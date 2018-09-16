package ui;

import control.PlayerInitialData;

public interface IRoot
{
    public void changeToMainWindow(PlayerInitialData playerInitialData);

    public void changeToTitleScreen();

    public void changeToCharacterCreation();
}
