package testesJunit;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import main.controller.actions.ButtonMenuListener;
import main.view.components.MenuLateral;
import main.view.components.TelaSwitch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ButtonMenuListenerTest {

    private MenuLateral menuLateral;
    private TelaSwitch telaSwitch;

    @BeforeEach
    void setUp() {
        menuLateral = new MenuLateral();
        telaSwitch = new TelaSwitch();
    }

}

