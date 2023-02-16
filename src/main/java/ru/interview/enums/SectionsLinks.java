package ru.interview.enums;

import lombok.Getter;

@Getter
public enum SectionsLinks {

    BASIC("Типы данных","https://javastudy.ru/interview/basics-types-operators-arrays/" ),
    OOP("ООП", "https://javastudy.ru/interview/java-oop/"),
    EXCEPTIONS("Исключения","https://javastudy.ru/interview/exceptions/"),
    COLLECTIONS("Коллекции","https://javastudy.ru/interview/collections/"),
    PEACEMAKER("Строки","https://javastudy.ru/interview/strings/"),
    IO("Потоки ввода/вывода","https://javastudy.ru/interview/input-output/"),
    CONCURRENT("Потоки выполнения/многопоточность","https://javastudy.ru/interview/concurrent/");

    private final String name;
    private final String url;

    SectionsLinks(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
