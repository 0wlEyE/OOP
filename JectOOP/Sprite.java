import java.awt.Image;

public class Sprite {

        protected boolean visible;
        private Image image;
        protected int POS_X;
        protected int POS_Y;
        protected boolean dying;
        protected int direction;

        public Sprite() {
            visible = true;
        }

        public void die() {
            visible = false;
        }

        public boolean isVisible() {
            return visible;
        }

        protected void setVisible(boolean visible) {
            this.visible = visible;
        }

        public void setImage(Image image) {
            this.image = image;
        }

        public Image getImage() {
            return image;
        }

        public void setX(int x) {
            this.POS_X = x;
        }

        public void setY(int y) {
            this.POS_Y = y;
        }
        public int getY() {
            return POS_Y;
        }

        public int getX() {
            return POS_X;
        }

        public void setDying(boolean dying) {
            this.dying = dying;
        }

        public boolean isDying() {
            return this.dying;
        }
}