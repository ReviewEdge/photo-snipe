package com.example.at3photosnipe

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MyDateUtils {

    companion object {
        private const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
        private const val NEW_DATE_FORMAT = "hh:mm a - MM/dd"

        // Function to get the current date and time as a formatted string
        fun getCurrentDateTimeString(): String {
            val currentDate = Date()
            val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT)
            return sdf.format(currentDate)
        }

        // Function to convert a date string to a Date object
        fun parseDateString(dateString: String): Date {
            val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT)
            return sdf.parse(dateString) ?: Date()
        }

        // Function to format a Date object to a string with a custom format
        fun formatDateToString(date: Date, customFormat: String): String {
            val sdf = SimpleDateFormat(customFormat)
            return sdf.format(date)
        }

        // Function to compare two date strings
        fun compareDateStrings(dateString1: String, dateString2: String): Int {
            val date1 = parseDateString(dateString1)
            val date2 = parseDateString(dateString2)
            return date1.compareTo(date2)
        }


        // Function to format a date string to a custom format
        fun formatDateStringCustom(dateString: String): String {
            val date = parseDateString(dateString)
            val today = Calendar.getInstance().time
            val sdfTimeOnly = SimpleDateFormat("hh:mm a")

            return when {
                isSameDay(date, today) -> "${sdfTimeOnly.format(date)} - Today"
                else -> SimpleDateFormat(NEW_DATE_FORMAT).format(date)
            }
        }

        private fun isSameDay(date1: Date, date2: Date): Boolean {
            val cal1 = Calendar.getInstance()
            val cal2 = Calendar.getInstance()
            cal1.time = date1
            cal2.time = date2
            return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                    cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
        }
    }
}
