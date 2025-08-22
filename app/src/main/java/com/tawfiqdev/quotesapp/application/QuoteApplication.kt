package com.tawfiqdev.quotesapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Sert à démarrer Hilt et son conteneur racine.
 */
@HiltAndroidApp
class QuoteApplication : Application()