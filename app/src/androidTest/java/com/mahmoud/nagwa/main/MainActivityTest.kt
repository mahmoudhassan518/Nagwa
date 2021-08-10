package com.mahmoud.nagwa.main


import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import com.mahmoud.nagwa.scenarios.MainTestScenario
import org.junit.Test

class MainActivityTest : TestCase() {


    @Test
    fun main_activity_test_case() = run {
        step("run main test scenario without navigating to another screens") {
            scenario(MainTestScenario())
        }
    }

}