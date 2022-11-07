package com.artbird.onsite.ui.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


// fileName --- fileName without extension
fun generatePDF(fileName: String, directory: File, pdfDocument: PdfDocument): File? {

    val file = File(directory, "${fileName}2.pdf")
    Log.i("zlk", file.path)
    return try {
        pdfDocument.writeTo(FileOutputStream(file))
        file
    } catch (ex: IOException) {
        ex.printStackTrace()
        pdfDocument.close()
        null
    }
}

// draw logo
fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}