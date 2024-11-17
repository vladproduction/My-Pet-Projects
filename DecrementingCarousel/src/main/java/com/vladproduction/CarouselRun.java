package com.vladproduction;

public class CarouselRun {

    private  final DecrementingCarousel carousel;
    private int index = 0;

    public CarouselRun(DecrementingCarousel carousel){
        this.carousel = carousel;
    }

    public int next() {
        while(index < carousel.getCurrentSize()){
            int currentValue = carousel.getElements()[index];
            if(currentValue > 0){
                carousel.getElements()[index]--; //decrementing current value
                return currentValue; //return original
            }
            index++; //if current element is 0 move to the next
        }
        return -1; //task profile: there is no more elements to decrease
    }

    public boolean isFinished() {
        for (int i = 0; i < carousel.getCurrentSize(); i++) {
            if(carousel.getElements()[i] > 0){
                return false; //here false because still at least one element is to be done
            }
        }
        return true; //all elements are done depleted
    }

}
