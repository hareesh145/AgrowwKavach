package com.ak.util

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.UUID


object Utils {

    private var loaderDialog: LoaderDialog? = null

    private const val TAG = "Doctnow"
    fun logD(message: String) {
        Log.d(TAG, message)
    }

    fun getDeviceID(context: Context): String? {
        return Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.currentFocus
        if (view != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

//    var progressDialog: ProgressDialog? = null

    fun showProgessBar(context: Context?) {

        if (loaderDialog != null && loaderDialog!!.isShowing) return
        loaderDialog = LoaderDialog(context!!)
        loaderDialog?.setCancelable(false)
        loaderDialog?.show()

        /*        if (progressDialog != null && progressDialog!!.isShowing) return
                progressDialog = ProgressDialog(context)
                progressDialog!!.setMessage("Loading")
                progressDialog!!.setCancelable(false)
                progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
                progressDialog!!.show()
         */
    }


    fun hideProgressBar() {
        if (loaderDialog != null && loaderDialog!!.isShowing) loaderDialog?.dismiss()
//        if (progressDialog != null && progressDialog!!.isShowing) progressDialog!!.dismiss()
    }

    fun convertDateFormat(date: String?): String? {
        return try {
            val simpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            val format = SimpleDateFormat("dd MMM yyyy")
            format.format(simpleDateFormat.parse(date))
        } catch (e: Exception) {
            date
        }
    }

    fun convertDateFormatWithTime(date: String?): String? {
        return try {
            val simpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
            val format = SimpleDateFormat("dd MMM yyyy hh:mm a")
            format.format(simpleDateFormat.parse(date)).uppercase(Locale.getDefault())
        } catch (e: Exception) {
            date
        }
    }

    fun convertDateFormatWithT(date: String?): String? {
        return try {
            val simpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS")
            val format = SimpleDateFormat("dd MMM yyyy")
            format.format(simpleDateFormat.parse(date))
        } catch (e: Exception) {
            date
        }
    }

    fun convertTimeFormat(time: String?): String? {
        return try {
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val format = SimpleDateFormat("hh:mm a")
            format.format(simpleDateFormat.parse(time))
        } catch (e: Exception) {
            time
        }
    }

    //    public static String convertUstToIst(String dateValue) {
    //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
    //            throw new UnsupportedOperationException("This method requires API level 26 or higher.");
    //        }
    //        LocalDateTime localDateTime = LocalDateTime.parse(dateValue);
    //        OffsetDateTime utcDateTime = localDateTime.atOffset(ZoneOffset.UTC);
    //        ZoneId istZone = ZoneId.of("Asia/Kolkata");
    //        OffsetDateTime istDateTime = utcDateTime.atZoneSameInstant(istZone).toOffsetDateTime();
    //
    //        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM- hh:mm a");
    //        String formattedDate = outputFormatter.format(istDateTime);
    //
    //        int amPmPos = formattedDate.lastIndexOf(" ");
    //        if (amPmPos != -1) {
    //            String beforeAmPm = formattedDate.substring(0, amPmPos + 1);
    //            String amPm = formattedDate.substring(amPmPos + 1).toUpperCase();
    //            formattedDate = beforeAmPm + amPm;
    //        }
    //        return formattedDate;
    //    }
    fun convertUstToIst(dateValue: String?): String? {
        return if (dateValue == null) {
            // Handle the case where the input dateValue is null
            "Invalid date"
        } else try {
            // Define the expected date format
            var inputFormatter: DateTimeFormatter? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                inputFormatter = DateTimeFormatter.ISO_DATE_TIME
            }
            var outputFormatter: DateTimeFormatter? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a")
            }
            var localDateTime: LocalDateTime? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                localDateTime = LocalDateTime.parse(dateValue, inputFormatter)
            }
            var utcDateTime: OffsetDateTime? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                utcDateTime = localDateTime!!.atOffset(ZoneOffset.UTC)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val istZone = ZoneId.of("Asia/Kolkata")
            }
            var istDateTime: OffsetDateTime? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                istDateTime = utcDateTime!!.withOffsetSameInstant(ZoneOffset.of("+05:30"))
            }
            var formattedDate: String? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                formattedDate = outputFormatter!!.format(istDateTime)
            }
            val amPmPos = formattedDate!!.lastIndexOf(" ")
            if (amPmPos != -1) {
                val beforeAmPm = formattedDate.substring(0, amPmPos + 1)
                val amPm = formattedDate.substring(amPmPos + 1).uppercase(Locale.getDefault())
                formattedDate = beforeAmPm + amPm
            }
            formattedDate
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            "Error parsing date"
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertUstToIstTime(timeValue: String?): String? {
        return if (timeValue == null) {
            "Invalid time"
        } else try {
            val utcTime = LocalTime.parse(timeValue, DateTimeFormatter.ofPattern("H:mm"))
            val utcZone = ZoneId.of("UTC")
            val currentDate = LocalDate.now(utcZone)
            val utcDateTime = ZonedDateTime.of(currentDate, utcTime, utcZone)

            val istZone = ZoneId.of("Asia/Kolkata")
            val istDateTime = utcDateTime.withZoneSameInstant(istZone)

            val formatter = DateTimeFormatter.ofPattern("hh:mm a")
            val istTime = istDateTime.format(formatter)

            istTime
        } catch (e: Exception) {
            e.printStackTrace()
            "Error parsing time"
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun convertUstToIstTimeWithSeconds(timeValue: String?): String? {
        return if (timeValue == null) {
            "Invalid time"
        } else try {
            val colonCount: Int = timeValue.split(":").count() - 1
            if (colonCount == 1) {
                val utcTime = LocalTime.parse(timeValue, DateTimeFormatter.ofPattern("H:mm"))
                val utcZone = ZoneId.of("UTC")
                val currentDate = LocalDate.now(utcZone)
                val utcDateTime = ZonedDateTime.of(currentDate, utcTime, utcZone)

                val istZone = ZoneId.of("Asia/Kolkata")
                val istDateTime = utcDateTime.withZoneSameInstant(istZone)

                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val istTime = istDateTime.format(formatter)

                istTime
            } else {
                val utcTime = LocalTime.parse(timeValue, DateTimeFormatter.ofPattern("H:mm:ss"))
                val utcZone = ZoneId.of("UTC")
                val currentDate = LocalDate.now(utcZone)
                val utcDateTime = ZonedDateTime.of(currentDate, utcTime, utcZone)

                val istZone = ZoneId.of("Asia/Kolkata")
                val istDateTime = utcDateTime.withZoneSameInstant(istZone)

                val formatter = DateTimeFormatter.ofPattern("hh:mm a")
                val istTime = istDateTime.format(formatter)

                istTime

            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error parsing time"
        }
    }


    fun nonEmptyText(timeOfDay: String?, day: String?, time: String?): String {
        var dosage: String = ""
        if (!timeOfDay.isNullOrEmpty()) {
            dosage = timeOfDay
        }
        if (!day.isNullOrEmpty()) {
            if (dosage.isEmpty()) {
                dosage = day
            } else {
                dosage += ", $day"
            }
        }
        if (!time.isNullOrEmpty()) {
            if (dosage.isEmpty()) {
                dosage = time
            } else {
                dosage += ", $time"
            }
        }
        return dosage

    }

    fun showSnackBar(view: View?, message: String?) {
        Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG).show()
    }




    fun savePrescription(body: ResponseBody): File? {
        return try {
            // Get the external storage directory
            val pdfFile = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "Test${System.currentTimeMillis()}.pdf"
            )

            var inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null

            try {
                val fileSize = body.contentLength()
                val fileReader = ByteArray(fileSize.toInt())
                var fileSizeDownloaded: Long = 0

                inputStream = body.byteStream()
                outputStream = FileOutputStream(pdfFile)

                while (true) {
                    val read = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read
                }
                outputStream.flush()
                pdfFile
            } catch (e: Exception) {
                null
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    var totalFileSize = 0
    var output: FileOutputStream? = null


    fun saveFileToPDF(body: ResponseBody, context: Context): File? {
        try {
            val fileName = "DC_${System.currentTimeMillis()}" + "_Prescription.pdf"
            /*File mediaFile1 = new File(mediaFile.getPath() + File.separator
                + "Invoice"+ timeStamp + ".pdf");*/
            // val decodedBytes: ByteArray = body.bytes(), Base64.DEFAULT
            val outputFile = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            )
            /* output = FileOutputStream(outputFile)
             outputFile.outputStream().use {
                 it.write(body.bytes())
             }*/
            var inputStream: InputStream? = null
            var outputStream: FileOutputStream? = null

            try {
                inputStream = body.byteStream()
                outputStream = FileOutputStream(outputFile)

                // Log content length
                val totalBytes = body.contentLength()
                println("Total bytes to download: $totalBytes")

                if (totalBytes <= 0) {
                    println("Error: Content length is zero or negative.")
                    //return false
                }

                // Buffer to read bytes
                val buffer = ByteArray(4096)
                var bytesRead: Int
                var bytesCopied = 0L

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                    bytesCopied += bytesRead
                    println("Bytes copied: $bytesCopied / $totalBytes")
                }
                outputStream?.flush()
            } catch (e: Exception) {

            } finally {
                // Close streams safely
                inputStream?.close()
                outputStream?.close()
            }


            return outputFile
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Toast.makeText(context, "Try again", Toast.LENGTH_LONG).show()
        } finally {
            output?.close()
        }
        return null
    }

    fun getLocalTime(): String? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val currentTime = LocalTime.now()
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val formattedTime = currentTime.format(formatter)
            println("Formatted Current Time: $formattedTime")
            formattedTime
        } else {
            val currentTime = Calendar.getInstance().time
            println("Current Time: $currentTime")
            currentTime.toString()
        }
    }

    fun convertTime(time: String?): String {
        return try {
            val simpleDateFormat = SimpleDateFormat("hh:mm")
            val format = SimpleDateFormat("hh:mm a")
            format.format(simpleDateFormat.parse(time))
        } catch (e: Exception) {
            "$time"
        }

    }

    fun convertFile(context: Context?, bitmap: Bitmap): File {
        val wrapper = ContextWrapper(context)
        var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
        file = File(file, "${UUID.randomUUID()}.jpg")
        val stream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 25, stream)
        stream.flush()
        stream.close()
        return file
    }

    fun getFileNameFromUri(context: Context, uri: Uri): String? {
        var fileName: String? = null
        val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)

        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
                fileName = cursor.getString(columnIndex)
            }
        }
        return fileName
    }

    fun isShowingProgress(): Boolean {
        return loaderDialog != null && loaderDialog!!.isShowing
//        return progressDialog!=null && progressDialog!!.isShowing
    }

    fun openPlayStore(context: Context) {
        val appPackageName = context.packageName
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$appPackageName")
                )
            )
        } catch (e: android.content.ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                )
            )
        }

    }

    fun setSpannableTextForLastAsterisk(textView: TextView, text: String) {
        val lastAsteriskIndex = text.lastIndexOf('*')

        if (lastAsteriskIndex != -1) {
            val spannableStringBuilder = SpannableStringBuilder(text)
            spannableStringBuilder.setSpan(
                ForegroundColorSpan(Color.RED),
                lastAsteriskIndex,
                lastAsteriskIndex + 1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            textView.text = spannableStringBuilder
        } else {
            textView.text = text
        }
    }


}