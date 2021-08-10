package com.mahmoud.nagwa.scenarios

import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext
import com.mahmoud.nagwa.main.MainActivityScreen
import com.mahmoud.nagwa.presentation.ui.main.MainActivity
import com.mahmoud.nagwa.testUtils.goto
import io.kotlintest.matchers.numerics.shouldBeGreaterThan

class MainTestScenario : Scenario() {


    val listScreen = MainActivityScreen()

    override val steps: TestContext<Unit>.() -> Unit = {
        step("open the main activity") {
            goto(MainActivity::class.java)
        }

        listScreen {

            step("check for data has been arrived from API or json file") {
                recyclerView {
                    step("size should be bigger than 0") {
                        getSize().shouldBeGreaterThan(0)
                    }

                    step("make sure first item is visible and has data") {
                        childAt<MainActivityScreen.Item>(0) {
                            titleTextView {
                                isVisible()
                                hasAnyText()
                            }
                            urlTextView {
                                isVisible()
                                hasAnyText()
                            }
                            step("download file click") {
                                downloadButton {
                                    isVisible()
                                    isClickable()
                                }
                                flakySafely(2_000) {
                                    downloadButton.click()
                                }
                            }
                        }

                    }
                }
            }
        }


    }
}