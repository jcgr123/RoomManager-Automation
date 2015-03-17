package framework.pages.admin.conferencerooms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import framework.selenium.SeleniumDriverManager;

/**
 * This class represents the Menu for conference rooms, Save and Cancel button
 * @author Yesica Acha
 *
 */
public abstract class RoomBaseAbstractPage {
	protected WebDriver driver;	
	protected WebDriverWait wait;

	@FindBy(linkText = "Room Info") 
	WebElement roomInfoLink;

	@FindBy(linkText = "Resource Associations") 
	WebElement resourceAssociationsLink;

	@FindBy(linkText = "Out of Order Planning") 
	WebElement outOfOrderPlanningLink;

	@FindBy(xpath = "//button[@ng-click='save()']") 
	WebElement saveBtn;

	@FindBy(xpath = "//button[@ng-click='cancel()']") 
	WebElement cancelBtn;
	
	@FindBy(xpath = "//small[contains(text(),' ')]")
	WebElement errorMessageLbl;

	public RoomBaseAbstractPage() {
		driver = SeleniumDriverManager.getManager().getDriver();
		wait = SeleniumDriverManager.getManager().getWait();
		PageFactory.initElements(driver, this);
	}

	public RoomInfoPage clickRoomInfoLink(){
		roomInfoLink.click();
		return new RoomInfoPage();
	}

	public RoomResourceAssociationsPage clickResourceAssociationsLink(){
		resourceAssociationsLink.click();
		return new RoomResourceAssociationsPage();
	}

	public RoomOutOfOrderPlanningPage clickOutOfOrderPlanningLink(){
		outOfOrderPlanningLink.click();
		return new RoomOutOfOrderPlanningPage();
	}

	public RoomPage clickCancelBtn(){
		cancelBtn.click();
		return new RoomPage();
	}
	
	public RoomPage clickSaveBtn(){
		saveBtn.click();
		return new RoomPage();
	}

	/**
	 * [YA] This method clicks Save button when an error message is expected and 
	 * it should stay in the same page
	 * @param errorMessage
	 * @return
	 */
	public Object clickSaveWithErrorBtn(){
		saveBtn.click();
		wait.until(ExpectedConditions.visibilityOf(errorMessageLbl));
		return this;
	}

	/**
	 * [YA]This method verifies if any error message is displayed
	 * @return
	 */
	public boolean isErrorMessagePresent() {
		return errorMessageLbl.isDisplayed();
	}
}