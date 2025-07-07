package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE,
            FOOTER_ELEMENT,
            BTN_SAVE_FOR_LIST,
            BTN_ADD_TO_LIST,
            TEXT_INPUT_TO_LIST,
            BTN_OK,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            ITEM_LIST_BY_TITLE_TPL;

    public ArticlePageObject(RemoteWebDriver driver){
            super(driver);
    }

    /* TEMPLATES METHODS */
    @Step("Поиск подстроки '{substring}' в элементе")
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    @Step("Поиск подстроки '{substring}' в элементе")
    private static String getItemTitleElementList(String substring){
        return ITEM_LIST_BY_TITLE_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    @Step("Поиск заголовка статьи")
    public WebElement waitForTitleElement(){
        return this.waitForElementPresent(TITLE, "Не найдена статья", 50);
    }

    @Step("Получение заголовка статьи")
    public String getArticleTitle(){

        WebElement title_element = waitForTitleElement();
        //return title_element.getAttribute("text");
        return this.getTextElement(title_element);
    }

    @Step("Свайп до конца статьи")
    public void swipeToFooter(){
        this.swipeUpToFindElement(FOOTER_ELEMENT,"Не найден конец статьи",20);
    }

    @Step("Закрытие карточки статьи")
    public void closeArticle(){
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON,"Не найдена кнопка возврата 'Navigate up'",5);
    }

    @Step("Создание списка для сохранения статей")
    public void createListAndSaveArticleInIt(String name_list) {

        this.waitForElementAndClick(BTN_SAVE_FOR_LIST,"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(BTN_ADD_TO_LIST,"Не найдена кнопка 'Add to list'",5);

        this.waitForElementAndSendKeys(TEXT_INPUT_TO_LIST, name_list,"Не найден элемент 'Name of this list'",5);
        this.waitForElementAndClick(BTN_OK,"Не найдена кнопка 'OK'",5);
    }

    @Step("Сохранение статьи в существующий список")
    public void addToExistListAnyArticle(String name_list){

        this.waitForElementAndClick(BTN_SAVE_FOR_LIST,"Не найдена кнопка 'Save' для сохранения в список",5);
        this.waitForElementAndClick(BTN_ADD_TO_LIST,"найдена кнопка 'Add to list'",5);

        this.waitForElementAndClick(getItemTitleElementList(name_list),"Не найден список '" + name_list + "'",5);
    }

    public void assertTitleArticleText(String text){
        this.assertElementHasText(TITLE, text,"Заголовок статьи не содержит текст '" + text + "'");

    }
}