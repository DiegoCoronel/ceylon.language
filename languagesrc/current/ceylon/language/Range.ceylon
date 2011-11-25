doc "Represents the range of totally ordered, ordinal values 
     generated by two endpoints of type `Ordinal` and 
     `Comparable`. If the first value is smaller than the
     last value, the range is increasing. If the first value
     is larger than the last value, the range is decreasing.
     If the two values are equal, the range contains exactly
     one element. The range is always nonempty, containing 
     at least one value."
by "Gavin"
shared class Range<Element>(Element first, Element last) 
        extends Object() 
        satisfies Sequence<Element> & Category & Equality
        given Element satisfies Ordinal<Element> & 
                                Comparable<Element> { 
    
    doc "The start of the range."
    shared actual Element first = first;
    
    doc "The end of the range."
    shared actual Element last = last;

    shared actual String string {
        return first.string + ".." + last.string;
    }
    
    doc "Determines if the range is decreasing."
    shared Boolean decreasing { 
        return last<first; 
    }
    
    Boolean pastEnd(Element x) {
        if (decreasing) {
            return x<last;
        }
        else {
            return x>last;
        }
    }
    
    Element next(Element x) {
        if (decreasing) {
            return x.predecessor;
        }
        else {
            return x.successor;
        }
    }

    variable Natural index:=0;
    variable Element x:=first;
    while (!pastEnd(x)) {
        ++index;
        x:=next(x);
    }
    
    doc "The nonzero number of elements in the range."
    shared actual Natural size = index;
    
    doc "The index of the end of the range."
    shared actual Natural lastIndex { 
        return size-1; 
    }
    
    doc "The rest of the range, without the start of the
         range."
    shared actual Element[] rest {
        return Range<Element>(next(first),last);
    }
    
    doc "The element of the range that occurs `n` values after
         the start of the range. Note that this operation 
         is inefficient for large ranges."
    shared actual Element? item(Natural n) {
        //optimize this for numbers!
        variable Natural index:=0;
        variable Element x:=first;
        while (index<n && !pastEnd(x)) {
            ++index;
            x:=next(x);
        }
        if (pastEnd(x)) {
            return null;
        }
        else {
            return x;
        }
    }
    
    doc "An iterator for the elements of the range."
    shared actual Iterator<Element> iterator {
        class RangeIterator(Element current) 
                satisfies Iterator<Element> {
            shared actual Element head { 
                return current;
            }
            shared actual Iterator<Element>? tail {
                Element x = next(current);
                if (pastEnd(x)) {
                    return null;
                }
                else {
                    return RangeIterator(x);
                }
            }
        }
        return RangeIterator(first);
    }
    
    doc "Determines if the range includes the given object."
    shared actual Boolean contains(Equality element) {
        if (is Element element) {
            return includes(element);
        }
        else {
            return false;
        }
    }
    
    doc "Determines if the range includes the given value."
    shared Boolean includes(Element x) {
        if (decreasing) {
            return x<=first && x>=last;
        }
        else {
            return x>=first && x<=last;
        }
    }
    
    /*shared Element[] excludingLast {
        throw; //todo!
    }*/

    doc "Return a sequence of values in the range, beginning 
         at the first value, and incrementing by a constant 
         step size, until a value outside the range is 
         reached. In the case of a decreasing range, the
         sequence is generated using decrements of the step
         size."
    shared Element[] by(Natural stepSize) {
        //todo: should we just give Range a step size? Or
        //      add a subclass, perhaps?
        throw; //todo!
    }

    /*shared Natural? index(Element x) {
    if (!includes(x)) {
        return null;
    }
    else {
        //optimize this for numbers!
        variable Natural index:=0;
        variable Element value:=first;
        while (value<x) {
            ++index;
            ++value;
        }
        return index;
    }
    }*/

    doc "Determines if two ranges are the same by comparing
         their endpoints."
    shared actual Boolean equals(Equality that) {
        if (is Range<Element> that) {
            return that.first==first && that.last==last;
        }
        else {
            return false;
        }
    }
    
    shared actual Integer hash {
        return first.hash/2 + last.hash/2; //todo: really should be xor
    }
    
    doc "Returns the range itself, since ranges are 
         immutable."
    shared actual Range<Element> clone {
        return this;
    }
    
    /*shared actual Set<Element> elements {
        throw; //todo!
    }*/
    
    shared actual Element[] segment(Natural from, 
                                    Natural length) {
        throw; //todo!
    }
    
    shared actual Element[] span(Natural from, Natural to) {
        throw; //todo
    }
    
}