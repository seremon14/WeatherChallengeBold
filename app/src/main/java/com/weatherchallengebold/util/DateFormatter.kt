package com.weatherchallengebold.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateFormatter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val outputFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    fun formatForecastDate(dateString: String): String {
        return try {
            val date = dateFormat.parse(dateString) ?: return dateString
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val dateCalendar = Calendar.getInstance().apply {
                time = date
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }

            val tomorrow = Calendar.getInstance().apply {
                time = today.time
                add(Calendar.DAY_OF_YEAR, 1)
            }

            when {
                dateCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                        dateCalendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR) -> "Hoy"

                dateCalendar.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR) &&
                        dateCalendar.get(Calendar.DAY_OF_YEAR) == tomorrow.get(Calendar.DAY_OF_YEAR) -> "Mañana"

                else -> outputFormat.format(date).replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                }
            }
        } catch (e: Exception) {
            dateString
        }
    }
}
