package com.just3days.config

const val THREE_DAYS: Int = 259200 // Time (seconds)
const val NON_TRANSPARENCY: Int = 255

object AppConstants {
    const val PREF_NAME = "3days_pref"
    const val INFO = "작심의 내용을 중앙에 작성하고 하단의 心을 누르면 작심이 완성됩니다.\n\n" +
            "작심 내용은 3일동안 서서히 사라집니다.\n\n" +
            "작심이 사라지는 것을 막기 위해, 하단의 心을 눌러 작심을 다시 하시길 권해드립니다.\n\n" +
            "작심 내용을 변경하기 위해서는 3일 뒤에 기존의 작심이 완전히 사라지고 난 다음 새로 입력이 가능합니다.\n\n" +
            "한 번 작심을 할때 주의를 기울여 주세요.\n\n\n" +
            "* 결심의 내용은 짧게 작성해주세요. (최대 20자)\n\n" +
            "* 결심 후, 다시 心을 누르게되면 해당 시점부터 다시 3일이 진행됩니다."
}