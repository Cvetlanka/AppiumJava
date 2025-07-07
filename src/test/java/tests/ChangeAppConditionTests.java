package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("Тесты для проверки работы с экраном приложения")
public class ChangeAppConditionTests extends CoreTestCase {

    @Test // Седьмой тест из обучающего урока
    @DisplayName("Проверка выхода статьи из режима background")
    @Description("Ищем статью со слово 'Sport' в заголовке, проверяем заголовок после выхода приложения из режима background")
    @Step("Стартуем testCheckSearchArticleInBackground...")
    public void testCheckSearchArticleInBackground() {

        if(Platform.getInstance().IsMW()){
            return;
        }
        String search_line = "Sport";
        String article  = "Sporting CP";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForSearchResult(article);

        this.backgroundApp(5);
        SearchPageObject.waitForSearchResult(article);

        System.out.println("Седьмой тест из урока завершён!");
    }

    @Test // // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex7*: Поворот экрана)
    @DisplayName("Проверка поворотов экрана")
    @Description("Ищем статью со словом 'Sport' в заголовке, проверяем заголовок после двойного поворота экрана")
    @Step("Стартуем testChangeScreenOrientationOnScreenResults_Ex7...")
    public void testChangeScreenOrientationOnScreenResults_Ex7(){

        if(Platform.getInstance().IsMW()){
            return;
        }
        String search_line = "Sport";
        String article  = "Sporting CP";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Заголовок статьи изменился после первого поворота экрана!\n Был '" + title_before_rotation + "'.\n Стал '" + title_after_rotation + "'.",
                title_before_rotation,
                title_after_rotation
        );

        this.rotateScreenPortrait();

        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Заголовок статьи изменился после второго поворота экрана!\n Был '" + title_before_rotation + "'.\n Стал '" + title_after_second_rotation + "'.",
                title_before_rotation,
                title_after_second_rotation
        );

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex7*: Поворот экрана) завершён!");
    }
}
