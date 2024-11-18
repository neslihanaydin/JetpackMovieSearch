package com.nt.moviesappcompose.navigation.screen

import androidx.navigation.NamedNavArgument

interface Screen {
     fun route(): String

     val arguments: List<NamedNavArgument>
         get() = emptyList()
 }
