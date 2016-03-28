package org.swati.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Imagine you have an x-y grid of floats.
 * Define a method to drop a parameter block of a certain height and length (also floats) beginning at any x coordinate.
 * Subsequent blocks dropped will fall on top of any previous blocks, 'stacking' their height.
 * Define another method to return the height at any requested x location.  
 *
 * @author Swati Kumar
 * @since 1.0.0
 */
public class TetrisType {
    GridState gridState;

    class Block {
        Double start;
        Double height;
        Double length;

        Block(Double start, Double height, Double length) {
            this.start = start;
            this.height = height;
            this.length = length;
        }
    }

    class Section {
        Double xMin;
        Double xMax;
        Stack<Block> blocks;
        Double maxHeight;

        Section(Block block) {
            xMin = block.start;
            xMax = block.start + block.length;
            maxHeight = block.height;
            blocks = new Stack<Block>();
            blocks.push(block);
        }

        void addBlock(Block block) {
            this.xMax = Math.max(this.xMax, block.start + block.length);
            this.xMin = Math.min(this.xMin, block.start);
            this.maxHeight = this.maxHeight + block.height;
            this.blocks.push(block);
        }
    }

    class GridState {
        List<Section> sections;
        SectionComparator sectionComparator;

        GridState() {
            sections = new ArrayList<Section>();
            sectionComparator = new SectionComparator();
        }

        public void sort() {
            Collections.sort(sections, sectionComparator);
        }
    }

    class SectionComparator implements Comparator<Section> {

        public int compare(Section o1, Section o2) {
            return o2.maxHeight.compareTo(o1.maxHeight);
        }
    }

    public void initialize() {
        gridState = new GridState();
    }

    public void drop(double start, double height, double length) {
        Block block = new Block(start, height, length);
        boolean added = false;
        for (Section section : gridState.sections) {
            if (section.xMax >= block.start) {
                section.addBlock(block);
                added = true;
                break;
            }
        }
        if (!added) {
            gridState.sections.add(new Section(block));
        }
        gridState.sort();
    }

    public double getHeight(double xPos) {
        double maxHeight = 0;
        double totalHeight = 0;
        for (Section section : gridState.sections) {
            if (section.xMin <= xPos && section.xMax >= xPos) {
                totalHeight = 0;
                Stack<Block> blocks = new Stack<Block>();
                blocks.addAll(section.blocks);
                while (!blocks.isEmpty()) {
                    Block block = blocks.peek();
                    if (block.start <= xPos && (block.start + block.length) >= xPos) {
                        break;
                    }
                    blocks.pop();
                }

                for (Block block : blocks) {
                    totalHeight += block.height;
                }
            }
            if (maxHeight < totalHeight) {
                maxHeight = totalHeight;
            }
        }
        return maxHeight;
    }

    public static void main(String args[]) {
        TetrisType tetrisType = new TetrisType();
        tetrisType.initialize();
        //start, height, length
        tetrisType.drop(1, 2, 2);
        tetrisType.drop(4, 10, 1);
        tetrisType.drop(2, 4, 10);
        tetrisType.drop(4, 4, 1);

        double xPos = 1;
        System.out.println("height at " + xPos + " is " + tetrisType.getHeight(xPos));
    }
}
