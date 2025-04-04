package com.example.savingbyshopping.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object CalendarUtils {

    fun getCalendarDate() : Calendar {
        val calendar = Calendar.getInstance()
        return calendar
    }

    fun calendarToString(calendar: Calendar): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault() )
        return sdf.format(calendar.time)
    }

}