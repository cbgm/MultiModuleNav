@file:Suppress("unused")

package com.christian.multinavlib.navigation.extension

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import android.view.View

private fun FragmentManager.inBackStack(): Boolean {
   return if (backStackEntryCount > 1) {
      popBackStackImmediate()
      beginTransaction().commit()
      true
   } else {
      false
   }
}

fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String) {
   supportFragmentManager.inTransaction {
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)
   }
}

fun FragmentActivity.replaceFragmentNoBackStack(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String
) {
   supportFragmentManager.inTransaction {
      replace(frameId, fragment, backStackTag)

   }
}

fun FragmentActivity.replaceFragmentwithSharedElement(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String,
      sharedElement: View,
      transitionName: String
) {
   supportFragmentManager.inTransaction {
      addSharedElement(sharedElement, transitionName)
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)

   }
}


fun FragmentActivity.backStack() {
   if (!supportFragmentManager.inBackStack())
      finish()
}

fun FragmentActivity.backStackClean()
      : Boolean {
   return if (supportFragmentManager.backStackEntryCount > 1) {
      supportFragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
      true
   } else {
      false
   }
}