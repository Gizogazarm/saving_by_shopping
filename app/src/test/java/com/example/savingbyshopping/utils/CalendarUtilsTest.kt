package com.example.savingbyshopping.utils

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Calendar


class CalendarUtilsTest {

    @Test
    fun `test mendapatkan calendar to String`(){
        val expected = "07/04/2025" // buat tanggal hari ini
        val calendar = CalendarUtils.getCalendarDate()
        val actual = CalendarUtils.calendarToString(calendar)
        assertEquals(expected, actual)

    }

}