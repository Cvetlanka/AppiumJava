package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Тесты для проверки работы со списками для сохранения статей")
public class MyListsTests extends CoreTestCase {

    @Test // // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex5: Тест: Сохранение двух статей)
    @DisplayName("Проверка сохранения двух статей в избранное")
    @Description("Находим статью со словом поиска 'Sport'; создаем новый список 'My List of Sport'; находим и добавляем в него две статьи 'Sporting CP' и 'Sport of athletics'; первую удаляем; проверяем заголовок второй, оставшейся в списке")
    @Step("Стартуем testSaveArticleToMyListAndDeleteFirstArticle_Ex5...")
    public void testSaveArticleToMyListAndDeleteFirstArticle_Ex5() {

        String search_line = "Sport";
        String name_list = "My List of Sport";
        String first_article  = "Sporting CP";
        String second_article = "Sport of athletics";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(first_article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        if (Platform.getInstance().IsAndroid()) {
            ArticlePageObject.createListAndSaveArticleInIt(name_list);
            ArticlePageObject.closeArticle();
        }
        else if(Platform.getInstance().IsMW()){
            NavigationUI NavigationUI = NavigationUIFactory.get(driver);
            NavigationUI.clickMyList();
            NavigationUI.clickMainMenu();
            NavigationUI.clickMainPage();
        }

        SearchPageObject.clickByArticleWithSubstring(second_article);

        ArticlePageObject.addToExistListAnyArticle(name_list);
        ArticlePageObject.closeArticle();

        SearchPageObject.clickCancelSearch();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyList();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        MyListPageObject.openFolderByName(name_list);
        MyListPageObject.swipeByArticleToDelete(second_article);
        MyListPageObject.waitForArticleToAppearByTitle(first_article);
        MyListPageObject.waitForArticleAndClick(first_article);

        ArticlePageObject.assertTitleArticleText(first_article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex5: Тест: Сохранение двух статей) завершён!");
    }
}
