package com.artbird.onsite.ui.quote

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.artbird.onsite.domain.Quote
import com.artbird.onsite.ui.components.Field
//import com.artbird.onsite.ui.window.toInchString


fun QuotePdfPage(
    quote: Quote,
    pageHeight: Int = 1120,
    pageWidth: Int = 792,
    pageNumber: Int = 1
) : PdfDocument{
    val pdfDocument = PdfDocument()
//    val paint = Paint()
    val h1 = Paint()
    val title = Paint()
    val myPageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
    val myPage = pdfDocument.startPage(myPageInfo)
    val canvas: Canvas = myPage.canvas

    var lineHeight = 20f
    var y = 80f
    var x = 100f
    var left = 460f
    var titleWidth = 110f
//  draw logo
//    val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.logo))
//    val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 120, 120, false)
//    canvas.drawBitmap(scaleBitmap!!, 40f, 40f, paint)

    h1.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    h1.textSize = 26f

    title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    title.textSize = 16f

    // Color.parseColor("#00ff00")
    // Color.Black
    title.color =  Color.Black.toArgb() // ContextCompat.getColor(context, R.color.purple_200)

    // first line, left side Title
    canvas.drawText("Shutterlux", x, y, h1)
    // first line, right side Title
    canvas.drawText("Quote", x + left + 80f, y, h1)
    y += lineHeight

    // second and third line, left side
    canvas.drawText("Unit 9, 33 Casebridge CT", x, y, title)
    y += lineHeight
    canvas.drawText("Scarborough, ON, M1B 3J5", x, y, title)
    y += lineHeight * 2

//    canvas.drawText("Client: ${quote?.client!!.username!!}", 100f, 100f, title)
    canvas.drawText("Bill To", x, y, title)
    canvas.drawText("Quote #", x + left , y, title)
    canvas.drawText("12345", x + left + titleWidth , y, title)
    y += lineHeight

    canvas.drawText(quote.client.username, x, y, title)
    canvas.drawText("Quote Date", x + left , y, title)
    canvas.drawText("2022-09-20", x + left + titleWidth , y, title)
    y += lineHeight

    canvas.drawText(quote.address, x, y, title)
    canvas.drawText("P.O #", x + left , y, title)
    canvas.drawText("123456", x + left + titleWidth , y, title)
    y += lineHeight * 2

    // Table Header
    val columns = listOf(
        Field("Description","description", 220f),
        Field("Dimension (W x H)","dimension", 220f),
        Field("Area","area", 80f),
        Field("Amount","amount", 80f)
    )
    var x1 = x
    columns.forEach {
        canvas.drawText(it.label, x1, y, title)
        x1 += it.width
    }
    y += lineHeight

    // Table content
    x1 = x
    quote?.windows!!.forEach { it ->
        canvas.drawText("${it?.floor!!.name} ${it?.room!!.name} ${it?.window!!.name}", x1, y, title)
        x1 += columns[0].width
//        canvas.drawText("${toInchString(it?.width!!)} X ${toInchString(it?.height!!)}", x1, y, title)
        x1 += columns[1].width
        canvas.drawText("${it.area}", x1, y, title)
        x1 += columns[2].width
        canvas.drawText("$${it.total}",x1, y, title)
        x1 += columns[3].width

        y += lineHeight
        x1 = x
    }

    y += lineHeight
    canvas.drawText("Total: $${quote?.total}",x + left + 80f, y, title)


    title.typeface = Typeface.defaultFromStyle(Typeface.NORMAL)
    title.color = Color.Black.toArgb() // ContextCompat.getColor(context, R.color.purple_200)
    title.textSize = 15f
    title.textAlign = Paint.Align.CENTER

    canvas.drawText("Thank you", 396f, 560f, title)
    pdfDocument.finishPage(myPage)

    return pdfDocument
}


