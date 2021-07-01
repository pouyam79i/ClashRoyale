package org.gamedevs.clashroyale.model;

import javafx.scene.image.ImageView;

public class CardImageView extends ImageView {
    private boolean active = true;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
