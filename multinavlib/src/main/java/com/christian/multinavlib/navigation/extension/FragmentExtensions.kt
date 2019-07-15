@file:Suppress("unused")

package com.christian.multinavlib.navigation.extension

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


fun Fragment.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String) {
   childFragmentManager.inTransaction {
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)
   }
}

fun Fragment.replaceFragmentwithSharedElement(
      fragment: Fragment,
      frameId: Int,
      backStackTag: String,
      sharedElement: View,
      transitionName: String
) {
   childFragmentManager.inTransaction {
      addSharedElement(sharedElement, transitionName)
      addToBackStack(backStackTag)
      replace(frameId, fragment, backStackTag)

   }
}

fun Fragment.backStack() {
   if (!childFragmentManager.inBackStack())
      activity?.finish()
}

private fun FragmentManager.inBackStack(): Boolean {
   return if (backStackEntryCount > 1) {
      popBackStackImmediate()
      beginTransaction().commit()
      true
   } else {
      false
   }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commitAllowingStateLoss()
}