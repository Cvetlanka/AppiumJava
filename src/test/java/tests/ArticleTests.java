package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {

    @Test // Третий тест из обучающего урока
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String article_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Неожиданный заголовок!",
                "Java (programming language)",
                article_title
        );
        System.out.println("Третий тест из урока завершён!");
    }

    @Test // Четвертый тест из обучающего урока
    public void testSwipeArticle() {
        String search_line = "Appium";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.clickByArticleWithSubstring(search_line);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

        System.out.println("Четвертый тест из урока завершён!");
    }

    @Test // Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title)
    public void testAssertTitle_Ex6() {
        String search_line = "Sport";
        String article  = "porting CP";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring(article);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.assertTitleArticleText(article);

        System.out.println("Тест для ДОМАШНЕГО ЗАДАНИЯ (Ex6: Тест: assert title) завершён!");
    }
}
