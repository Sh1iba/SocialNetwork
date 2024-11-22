package com.example.social_network

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["src/test/resources/features"], // Укажите путь к .feature-файлам
    glue = ["com.example.social_network.steps"],      // Пакет с реализацией шагов
    plugin = ["pretty"]                        // Для красивого вывода в консоль
)
class CucumberTestRunner