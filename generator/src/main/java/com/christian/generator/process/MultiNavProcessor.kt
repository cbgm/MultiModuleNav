package com.christian.generator.process

import com.christian.annotation.FeatureCoordinator
import com.christian.annotation.MainCoordinator
import com.christian.annotation.RootCoordinator
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.*
import me.eugeniomarletti.kotlin.processing.KotlinAbstractProcessor
import org.koin.core.KoinComponent
import java.io.File
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement

@Suppress("unused")
@AutoService(Processor::class)
class MultiNavProcessor : KotlinAbstractProcessor() {
    
    companion object {
        const val KOTLIN_DIRECTORY_NAME = "kapt.kotlin.generated"
    }

    private val featureAnnotation = FeatureCoordinator::class.java
    private val mainAnnotation = MainCoordinator::class.java
    private val rootAnnotation = RootCoordinator::class.java
    private val classInfo = Pair("com.christian.multinavlib", "MultiNav")

    override fun process(annotations: Set<TypeElement>, roundEnv: RoundEnvironment): Boolean {
        val featureCoordinators = findFeatures(roundEnv)
        val mainCoordinator = findMain(roundEnv)
        val rootCoordinator = findRoot(roundEnv)
        startClassGeneration(featureCoordinators, mainCoordinator, rootCoordinator)
        return false
    }

    override fun getSupportedAnnotationTypes(): Set<String> =
        setOf(featureAnnotation.canonicalName, mainAnnotation.canonicalName, rootAnnotation.canonicalName)

    override fun getSupportedSourceVersion(): SourceVersion = SourceVersion.latest()

    private fun startClassGeneration(
        featureList: ArrayList<Pair<Pair<String, String>, Int>>,
        mainCoordinator: Pair<String, String>,
        rootCoordinator: Pair<String, String>
    ) {
        if (featureList.size == 0)
            return

        val featureMembers = ArrayList<Pair<MemberName, Int>>()
        val mainMember = MemberName(mainCoordinator.first, mainCoordinator.second)
        val rootMember = MemberName(rootCoordinator.first, rootCoordinator.second)

        featureList.forEach { featureMembers.add(Pair(MemberName(it.first.first, it.first.second), it.second)) }
        val generatedClass = TypeSpec.Companion.classBuilder(classInfo.second)
            .addSuperinterface(KoinComponent::class)
            .addFunction(buildInitMethod(mainMember, rootMember, featureMembers))
            .addFunction(buildLookupMethod(mainMember))
            .build()
        val generatedFile =
            FileSpec.builder(classInfo.first, classInfo.second).addImports().addType(generatedClass).build()
        val kaptKotlinGeneratedDir = options[KOTLIN_DIRECTORY_NAME]
        generatedFile.writeTo(File(kaptKotlinGeneratedDir!!))
    }

    private fun FileSpec.Builder.addImports(): FileSpec.Builder {
        this.addImport("org.koin.core", "inject")
        return this
    }


    private fun findFeatures(roundEnv: RoundEnvironment): ArrayList<Pair<Pair<String, String>, Int>> {
        val featureList = ArrayList<Pair<Pair<String, String>, Int>>()
        val jsonFields = roundEnv.getElementsAnnotatedWith(featureAnnotation)
        jsonFields.forEach {
            val state = it.getAnnotation(featureAnnotation).state
            val pack = elementUtils.getPackageOf(it).toString()
            val annotatedClassName = it.simpleName.toString()
            featureList.add(Pair(Pair(pack, annotatedClassName), state))
        }
        return featureList
    }

    private fun findMain(roundEnv: RoundEnvironment): Pair<String, String> {
        val jsonFields = roundEnv.getElementsAnnotatedWith(mainAnnotation)
        jsonFields.forEach {
            val pack = elementUtils.getPackageOf(it).toString()
            val annotatedClassName = it.simpleName.toString()
            return Pair(pack, annotatedClassName)
        }
        return Pair("", "")
    }

    private fun findRoot(roundEnv: RoundEnvironment): Pair<String, String> {
        val jsonFields = roundEnv.getElementsAnnotatedWith(rootAnnotation)
        jsonFields.forEach {
            val pack = elementUtils.getPackageOf(it).toString()
            val annotatedClassName = it.simpleName.toString()
            return Pair(pack, annotatedClassName)
        }
        return Pair("", "")
    }

    private fun buildLookupMethod(mainMember: MemberName): FunSpec {
        val mainClassState = ClassName("$mainMember", "States")
        return FunSpec.builder("getState").apply {
            addModifiers(KModifier.PRIVATE)
            addParameter("status", Int::class)
            returns(mainClassState)
            addStatement("return ${mainMember.simpleName}.States.values().get(status)")
        }.build()
    }

    private fun buildInitMethod(
        mainMember: MemberName,
        rootMember: MemberName,
        featureMembers: ArrayList<Pair<MemberName, Int>>
    ): FunSpec {
        val createCoordinatorManager = MemberName("com.christian.multinavlib.navigation.coordinator", "CoordinatorManager")
        return FunSpec.builder("init").apply {
            addStatement("val coordinatorManager: %M by inject()", createCoordinatorManager)
            addStatement("val ${mainMember.simpleName.toLowerCase()}: %M by inject()", mainMember)
            addStatement("val ${rootMember.simpleName.toLowerCase()}: %M by inject()", rootMember)
            for (member in featureMembers)
                addStatement("val ${member.first.simpleName.toLowerCase()}: %M by inject()", member.first)
            beginControlFlow("coordinatorManager.run")
            addStatement("registerApplicationPartCoordinator(${rootMember.simpleName.toLowerCase()})")
            addStatement("registerMainCoordinator(${mainMember.simpleName.toLowerCase()})")
            for (member in featureMembers)
                addStatement("registerFeatureCoordinator(getState(${member.second}), ${member.first.simpleName.toLowerCase()})")
            endControlFlow()
        }.build()
    }
}