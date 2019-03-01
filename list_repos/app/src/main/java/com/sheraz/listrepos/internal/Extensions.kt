package com.sheraz.listrepos.internal

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment


/**
 * AppCompatActivity Extension functions
 */

inline fun <reified T : Fragment> AppCompatActivity.findFragmentByTag(tag: String) : T? = supportFragmentManager.findFragmentByTag(tag) as? T

inline fun <reified T : Fragment> AppCompatActivity.findFragmentByTagWithAutoDismiss(tag: String) : T? {
    val fragment = supportFragmentManager.findFragmentByTag(tag) as? T
    when (fragment) {
        is DialogFragment -> fragment.dismiss()
    }
    return fragment
}