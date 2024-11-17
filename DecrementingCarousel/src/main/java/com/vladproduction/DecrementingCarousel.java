package com.vladproduction;

public class DecrementingCarousel {

    private final int[] elements;
    private final int capacity;
    private int currentSize = 0;
    private boolean isRunning = false;
    private CarouselRun carouselRun;

    public DecrementingCarousel(int capacity) {
        this.capacity = capacity;
        this.elements = new int[capacity];
    }

    public int[] getElements() {
        return this.elements;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public boolean addElement(int element){
        if(element <= 0 || isRunning || currentSize >= capacity){
            return false;
        }
        this.elements[this.currentSize++] = element;
        return true;
    }

    public CarouselRun run(){
        if(isRunning){
            return null; //not run possibly while it is already run state defined
        }
        isRunning = true;
        carouselRun = new CarouselRun(this);
        return carouselRun;
    }

}
