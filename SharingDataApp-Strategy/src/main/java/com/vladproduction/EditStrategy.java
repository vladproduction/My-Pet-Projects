package com.vladproduction;

// Interface for edit strategies
interface EditStrategy {
    Photo edit(Photo photo) throws EditException;
}

