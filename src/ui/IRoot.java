package ui;

import control.Grave;
import control.PlayerInitialData;

public interface IRoot
{
    public void changeToMainWindow(PlayerInitialData playerInitialData);

    public void changeToMainWindow();

    public void changeToTitleScreen();

    public void changeToCharacterCreation();

    public void changeToGameOver(Grave grave);

    public void changeToGraveyard();
}
