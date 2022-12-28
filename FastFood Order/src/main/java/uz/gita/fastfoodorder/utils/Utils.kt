package uz.gita.fastfoodorder.utils

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast


interface MyToast{
    fun makeText(text: String)
}

class MyToastImpl(private val context: Context): MyToast {
    override fun makeText(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}


fun Context.getImageFilePath(uri: Uri): String {
    val filePath = uri.path.toString().split('/')
    val imageId = filePath.last()
    Log.d("TTT", "FILE ID ${uri.path}  ${MediaStore.Images.Media.EXTERNAL_CONTENT_URI}")

    val projection = arrayOf(
        MediaStore.Images.Media.DATA
    )

    val cursore = this.contentResolver
        .query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )

    cursore?.let { cursor ->
        cursor.moveToFirst()
        cursor.columnCount
        Log.d("TTT", "COLUMN COUNT ${cursor.columnCount}  COUNT ${cursor.count} ${cursor.columnNames}")
        val imagePath = cursor.getString(0)
        cursor.close()
        return imagePath
    }
    return "";
}
@SuppressLint("Range")
fun Context.getPath(uri: Uri?): String {
    var path: String = "";
    var document_id: String = ""

    var cursor: Cursor? = uri?.let { contentResolver.query(it, null, null, null, null) }
    if (cursor != null) {
        if (cursor.moveToNext() && cursor.count > 0) {
            cursor.moveToFirst()
            document_id = cursor.getString(0)
            document_id = document_id.split('/').last()
            cursor.close()
        }
    }
    cursor = contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null, MediaStore.Images.Media._ID + " = ? ", arrayOf<String>(document_id), null
    )
    if (cursor != null) {
        if (cursor.moveToNext() && cursor.count > 0) {
            cursor.moveToFirst()
            path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            cursor.close()
        }
    }
    return path
}