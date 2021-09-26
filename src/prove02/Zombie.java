package prove02;

import java.awt.*;
import java.util.Random;

public class Zombie extends Creature implements Movable, Aggressor {


        Random _rand;

        /**
         * Creates an zombie with 1 health point.
         */
        public Zombie() {
            _rand = new Random();
            _health = 1;
        }

        // No javadocs are necessary for these methods because they will inherit the
        // documentation from the superclass. We only need to add docs here if we are
        // doing something non-obvious in our overridden version.

        public Boolean isAlive() {
            return _health > 0;
        }

        public Shape getShape() {
            return Shape.Square;
        }

        public Color getColor() {
            return new Color(0, 0, 200);
        }

        /**
         * If the creature we've encountered is a plant, we'll eat it. Otherwise, we ignore it.
         * @param target The {@link Creature} we've encountered.
         */
        public void attack(Creature target) {
            // Animals only eat plants. Give the plant 1 damage
            // and increase our health by one.
            if(target instanceof Plant) {
                target.takeDamage(10);
                _health++;
            } else if (target instanceof Animal) {
                target.takeDamage(2);
                _health += 2;
            } else if (target instanceof Wolf) {
                target.takeDamage(4);
                _health += 3;
            } else {
                target.takeDamage(3);
            }
        }

        /**
         * Move the zombie in a random direction.
         */
        public void move() {

            // Choose a random direction each time move() is called.
            switch(_rand.nextInt(2)) {
                case 0:
                    _location.x++;
                    break;
                case 1:
                    _location.x--;
                    break;
                default:
                    break;
           }
        }
}
