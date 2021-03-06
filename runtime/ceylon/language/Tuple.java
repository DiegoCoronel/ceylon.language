package ceylon.language;

import static com.redhat.ceylon.compiler.java.Util.toInt;
import static com.redhat.ceylon.compiler.java.runtime.metamodel.Metamodel.getTypeDescriptor;
import static java.lang.System.arraycopy;

import java.lang.ref.SoftReference;
import java.util.Arrays;

import ceylon.language.impl.BaseIterator;

import com.redhat.ceylon.compiler.java.Util;
import com.redhat.ceylon.compiler.java.metadata.Annotation;
import com.redhat.ceylon.compiler.java.metadata.Annotations;
import com.redhat.ceylon.compiler.java.metadata.Ceylon;
import com.redhat.ceylon.compiler.java.metadata.Class;
import com.redhat.ceylon.compiler.java.metadata.FunctionalParameter;
import com.redhat.ceylon.compiler.java.metadata.Ignore;
import com.redhat.ceylon.compiler.java.metadata.Name;
import com.redhat.ceylon.compiler.java.metadata.SatisfiedTypes;
import com.redhat.ceylon.compiler.java.metadata.Transient;
import com.redhat.ceylon.compiler.java.metadata.TypeInfo;
import com.redhat.ceylon.compiler.java.metadata.TypeParameter;
import com.redhat.ceylon.compiler.java.metadata.TypeParameters;
import com.redhat.ceylon.compiler.java.metadata.Variance;
import com.redhat.ceylon.compiler.java.runtime.metamodel.Metamodel;
import com.redhat.ceylon.compiler.java.runtime.model.ReifiedType;
import com.redhat.ceylon.compiler.java.runtime.model.TypeDescriptor;

@Ceylon(major = 7)
@Class(extendsType = "ceylon.language::Object", 
       basic = false, identifiable = false)
@Annotations({
        @Annotation(
                value = "doc",
                arguments = {"A _tuple_ is a typed linked list. Each instance of \n`Tuple` represents the value and type of a single link.\nThe attributes `first` and `rest` allow us to retrieve\na value form the list without losing its static type \ninformation.\n\n    value point = Tuple(0.0, Tuple(0.0, Tuple(\"origin\")));\n    Float x = point.first;\n    Float y = point.rest.first;\n    String label = point.rest.rest.first;\n\nUsually, we abbreviate code involving tuples.\n\n    [Float,Float,String] point = [0.0, 0.0, \"origin\"];\n    Float x = point[0];\n    Float y = point[1];\n    String label = point[2];\n\nA list of types enclosed in brackets is an abbreviated \ntuple type. An instance of `Tuple` may be constructed \nby surrounding a value list in brackets:\n\n    [String,String] words = [\"hello\", \"world\"];\n\nThe index operator with a literal integer argument is a \nshortcut for a chain of evaluations of `rest` and \n`first`. For example, `point[1]` means `point.rest.first`.\n\nA _terminated_ tuple type is a tuple where the type of\nthe last link in the chain is `Empty`. An _unterminated_ \ntuple type is a tuple where the type of the last link\nin the chain is `Sequence` or `Sequential`. Thus, a \nterminated tuple type has a length that is known\nstatically. For an unterminated tuple type only a lower\nbound on its length is known statically.\n\nHere, `point` is an unterminated tuple:\n\n    String[] labels = ... ;\n    [Float,Float,String*] point = [0.0, 0.0, *labels];\n    Float x = point[0];\n    Float y = point[1];\n    String? firstLabel = point[2];\n    String[] allLabels = point[2...];"}),
        @Annotation(
                value = "by",
                arguments = {"Gavin"}),
        @Annotation("shared"),
        @Annotation("final")})
@SatisfiedTypes({
        "ceylon.language::Sequence<Element>"})
@TypeParameters({
        @TypeParameter(
                value = "Element",
                variance = Variance.OUT,
                satisfies = {},
                caseTypes = {}),
        @TypeParameter(
                value = "First",
                variance = Variance.OUT,
                satisfies = {"Element"},
                caseTypes = {}),
        @TypeParameter(
                value = "Rest",
                variance = Variance.OUT,
                satisfies = {"ceylon.language::Sequential<Element>"},
                caseTypes = {},
                defaultValue = "ceylon.language::Empty")})
public final class Tuple<Element, First extends Element, 
                Rest extends Sequential<? extends Element>>
        implements ReifiedType, Sequence<Element> {

    /** 
     * A backing array. May be shared between many Tuple instances
     * (Flyweight pattern).
     */
    @Ignore
    final java.lang.Object[] array;
    
    @Ignore
    private TypeDescriptor $reifiedElement;
    
    /** 
     * The rest of the elements, after those in the array.
     * This should never be another Tuple instance though.
     */
    private Sequential rest;

    /**
     * The ultimate constructor
     * @param $reifiedElement
     * @param array A backing array
     * @param first index into the {@code array} of the first element of this tuple
     * @param length number of elements in the {@code array} that are part of this tuple
     * @param rest index into the {@code array} of the first element of the {@code rest} of this tuple
     * @param copy whether to copy the array of not.
     */
    @Ignore
    public Tuple(@Ignore TypeDescriptor $reifiedElement, 
            java.lang.Object[] array, Sequential rest, boolean copy) {
        this.$ceylon$language$Category$this = 
                new Category$impl<java.lang.Object>(Object.$TypeDescriptor$, this);
        this.$ceylon$language$Iterable$this = 
                new Iterable$impl<Element,java.lang.Object>($reifiedElement, 
                        TypeDescriptor.NothingType, this);
        this.$ceylon$language$Collection$this = 
                new Collection$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Correspondence$this = 
                new Correspondence$impl<Integer,Element>(Integer.$TypeDescriptor$, 
                        $reifiedElement, this);
        this.$ceylon$language$List$this = 
                new List$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Sequence$this = 
                new Sequence$impl<Element>($reifiedElement, this);
        this.$ceylon$language$Sequential$this = 
                new Sequential$impl<Element>($reifiedElement, this);
        int length = array.length;
        if (array.length==0 || 
                length == 0 ||
                array.length <= 0) {
            throw new AssertionError("Tuple may not have zero elements");
        }
        if (length > array.length) {
            throw new AssertionError("Overflow :" + 
                    (length) + " > " + array.length);
        }
        this.$reifiedElement = $reifiedElement;
        if (copy) {
            this.array = (Element[])Arrays.copyOfRange(array, 
                  0, Util.toInt(length));
            
        } else {
            this.array = (Element[])array;
        }
        this.rest = rest;
        
        if (this.array == null) {
            throw new AssertionError("");
        }
        if (this.array.length == 0) {
            throw new AssertionError("");
        }
        if (this.rest == null) {
            throw new AssertionError("");
        }
    }
    
    /**
     * The Ceylon initializer constructor
     */
    public Tuple(@Ignore
            final TypeDescriptor $reifiedElement, 
            @Ignore
            final TypeDescriptor $reifiedFirst, 
            @Ignore
            final TypeDescriptor $reifiedRest, 
            @Name("first")
            @TypeInfo("First")
            final First first, @Name("rest")
            @TypeInfo("Rest")
            final Rest rest) {
        this($reifiedElement, makeArray(first, rest), makeRest(rest));
    }
    // where
    @Ignore
    private Tuple(TypeDescriptor $reifiedElement, java.lang.Object[] array,
            Sequential<?> rest) {
        this($reifiedElement, array, rest, false);
    }
    private static java.lang.Object[] makeArray(java.lang.Object first,
            Sequential<?> rest) {
        final java.lang.Object[] array;
        if (rest instanceof Tuple) {
            Tuple other = (Tuple)rest;
            array = new java.lang.Object[1 + other.array.length];
            array[0] = first;
            System.arraycopy(other.array, 0, array, 1, other.array.length);
            rest = other.rest;
        } else {
            array = new java.lang.Object[]{first};
        }
        return array;
    }
    private static Sequential<?> makeRest(Sequential<?> rest) {
        if (rest instanceof Tuple) {
            return ((Tuple)rest).rest;
        }
        return rest;
    }
    
    @Ignore
    public Tuple(TypeDescriptor $reifiedElement, 
            java.lang.Object[] elements) {
        this($reifiedElement, elements, empty_.get_(), false);
    }
    

    /**
     * The constructor used by the transformation of {@code [foo, bar *baz]}
     */
    @Ignore
    public static Tuple instance(TypeDescriptor $reifiedElement, 
            java.lang.Object[] elements, 
            Sequential<?> tail) {
        Sequential<?> rest;
        java.lang.Object[] array;
        if (tail instanceof Tuple) {
            Tuple<?,?,?> other = (Tuple<?,?,?>)tail;
            array = new java.lang.Object[elements.length + other.array.length];
            System.arraycopy(elements, 0, array, 0, elements.length);
            System.arraycopy(other.array, 0, array, elements.length, other.array.length);
            rest = other.rest;
        } else {
            array = elements;
            rest = tail;
        }
        return new Tuple($reifiedElement, array, rest, false);
    }
    
    public static Tuple instance(TypeDescriptor $reifiedElement, 
            java.lang.Object[] elements) {
        return instance($reifiedElement, elements, empty_.get_());
    }
    
    @Ignore
    protected TypeDescriptor $getReifiedElement$() {
        return ((TypeDescriptor.Class)$getType$()).getTypeArguments()[0];
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("First")
    @SuppressWarnings("unchecked")
    public final First getFirst() {
        return (First)array[0];
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("Rest")
    @SuppressWarnings({"unchecked", "rawtypes"})
    public Rest getRest() {
        if (getSize()==1) {
            return (Rest)empty_.get_();
        }
        else {
            
            return (Rest) new Tuple(((TypeDescriptor.Class)((TypeDescriptor.Class)$getType$()).getTypeArguments()[2]).getTypeArguments()[0],
                    Arrays.copyOfRange(this.array, 1, this.array.length), rest, false);
        }
    }

    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Integer")
    @Transient
    public final long getSize() {
        return array.length + rest.getSize();
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Null|Element")
    public final Element getFromFirst(@Name("index")
    @TypeInfo("ceylon.language::Integer")
    final long index) {
        if (index < 0) {
            return null;
        } else if (index >= array.length) {
            return (Element)rest.getFromFirst(index-array.length);
        }
        else {
            return (Element)array[toInt(index)];
        }
    }
    
    @Ignore
    @Override
    public Element getFromLast(long index) {
        if (index < 0) {
            return null;
        } else if (index >= array.length) {
            return (Element)rest.getFromLast(index - this.array.length);
        }
        return (Element)array[array.length-1-toInt(index)];
    }

    @Ignore
    @Override
    public final Element get(Integer index) {
        return getFromFirst(index.value);
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Integer")
    @Transient
    public final ceylon.language.Integer getLastIndex() {
        return Integer.instance(array.length + rest.getSize() - 1);
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("Element")
    @Transient
    public final Element getLast() {
        if (!rest.getEmpty()) {
            return (Element)rest.getLast();
        }
        return (Element)array[array.length - 1];
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Sequence<Element>")
    @Transient
    public final ceylon.language.Sequence<? extends Element> 
    getReversed() {
        java.lang.Object[] reversed = new java.lang.Object[Util.toInt(getSize())];
        int jj = 0;
        for (int ii = Util.toInt(this.rest.getSize())-1; ii >= 0; ii--) {
            reversed[jj++] = rest.getFromFirst(ii);
        }
        for (int ii = this.array.length-1; ii >= 0; ii--) {
            reversed[jj++] = this.array[ii];
        }
        return new Tuple($reifiedElement, reversed, empty_.get_(), false);
    }
    
    @Override
    @Ignore
    public List<? extends Element> sublistFrom(long index) {
        return $ceylon$language$List$this.sublistFrom(index);
    }
    
    @Override
    @Ignore
    public List<? extends Element> sublistTo(long index) {
        return $ceylon$language$List$this.sublistTo(index);
    }
    
    @Override
    @Ignore
    public List<? extends Element> sublist(long from, long to) {
        return $ceylon$language$List$this.sublist(from, to);
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Sequential<Element>")
    public final ceylon.language.Sequential<? extends Element> 
    segment(@Name("from")
    @TypeInfo("ceylon.language::Integer")
    final ceylon.language.Integer from, @Name("length")
    @TypeInfo("ceylon.language::Integer")
    long length) {
        long fromIndex = from.longValue();
        if (fromIndex < 0) {
            length = length+fromIndex;
            fromIndex = 0;
        }
        final long lastIndex = getLastIndex().longValue();
        
        if (fromIndex > lastIndex || length <= 0) {
            return (Sequential<? extends Element>)empty_.get_();
        }
        long l;
        if (length > lastIndex-fromIndex) {
            l = lastIndex-fromIndex+1;
        } else {
            l = length;
        }
        return new Tuple($reifiedElement, Arrays.copyOfRange(this.array, toInt(fromIndex), toInt(fromIndex+l)), empty_.get_(), false);
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Sequential<Element>")
    public final ceylon.language.Sequential<? extends Element> 
    span(@Name("from")
    @TypeInfo("ceylon.language::Integer")
    final ceylon.language.Integer from, @Name("end")
    @TypeInfo("ceylon.language::Integer")
    final ceylon.language.Integer end) {
        long fromIndex = Util.toInt(from.longValue());
        long toIndex = end==null ? getSize() : end.longValue();
        long lastIndex = getLastIndex().longValue();
        boolean reverse = toIndex<fromIndex;
        if (reverse) {
            long tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }
        if (toIndex<0 || fromIndex>lastIndex) {
            return (Sequential<? extends Element>)empty_.get_();
        }
        fromIndex= Math.max(fromIndex, 0);
        toIndex = Math.min(toIndex, lastIndex);
        if (reverse) {
            int fromInt = Util.toInt(fromIndex);
            int toInt = Util.toInt(toIndex);
            java.lang.Object[] reversed = new java.lang.Object[toInt-fromInt+1];
            for (int ii = toInt, jj = 0; ii >= fromIndex; ii--, jj++) {
                reversed[jj] = getFromFirst(ii);
            }
            return new Tuple($reifiedElement, reversed, empty_.get_(), false);
        }
        else {
            return new Tuple<Element, First, Rest>($getReifiedElement$(), 
                    Arrays.copyOfRange(this.array, toInt(fromIndex), toInt(toIndex)+1), empty_.get_(), false);
        }
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Sequential<Element>")
    public final ceylon.language.Sequential<? extends Element> 
    spanTo(@Name("to")
    @TypeInfo("ceylon.language::Integer")
    final ceylon.language.Integer to) {
        return to.longValue() < 0 ? 
                (Sequential<? extends Element>)empty_.get_() : 
                span(Integer.instance(0), to);
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Sequential<Element>")
    public final ceylon.language.Sequential<? extends Element> 
    spanFrom(@Name("from")
    @TypeInfo("ceylon.language::Integer")
    final ceylon.language.Integer from) {
        return span(from, Integer.instance(getSize()));
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Tuple<Element,First,Rest>")
    public final Tuple<Element, ? extends First, ? extends Rest> $clone() {
        return this;
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Iterator<Element>")
    public ceylon.language.Iterator<Element> iterator() {
        return new TupleIterator();
    }
    
    @Annotations({
            @Annotation("shared"),
            @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Boolean")
    public boolean contains(@Name("element")
    @TypeInfo("ceylon.language::Object")
    final java.lang.Object element) {
        for (int ii = 0; ii < array.length; ii++) {
            java.lang.Object x = array[ii];
            if (x!=null && element.equals(x)) return true;
        }
        return rest.contains(element);
    }
    
    @Ignore
    private SoftReference<TypeDescriptor> $cachedType = null;
    
    @Override
    @Ignore
    public TypeDescriptor $getType$() {
        TypeDescriptor type = $cachedType != null ? 
        		$cachedType.get() : null;
        if (type == null) {
            TypeDescriptor restType = getTypeDescriptor(rest);
			TypeDescriptor elementType = 
					Metamodel.getIteratedTypeDescriptor(restType);
			for (int ii = array.length-1; ii >= 0; ii--) {
			    TypeDescriptor elemType = $getElementType(ii);
			    elementType = TypeDescriptor.union(elementType, elemType);
			    restType = TypeDescriptor.klass(Tuple.class, 
			            elementType, elemType, restType);
			}
			type = restType;
            $cachedType = new SoftReference<TypeDescriptor>(type);
        }
        return type;
    }
    
    /*
    @Ignore
    private TypeDescriptor $getType(int offset) {
        if (offset < getSize()) {
            return TypeDescriptor.klass(Tuple.class, 
                    $getUnionOfAllType(offset), 
                    $getElementType(offset), 
                    $getType(offset + 1));
        } else {
            return empty_.$TypeDescriptor$;
        }
    }
    
    @Ignore
    private TypeDescriptor $getUnionOfAllType(int offset) {
        TypeDescriptor[] types = 
        		new TypeDescriptor[Util.toInt(getSize() - offset)];
        for (int i = 0; i < getSize() - offset; i++) {
            types[i] = $getElementType(offset + i);
        }
        return TypeDescriptor.union(types);
    }
    */
    
    @Ignore
    private TypeDescriptor $getElementType(int index) {
        return getTypeDescriptor(array[index]);
    }
    
    // The array length is the first element in the array
    @Ignore
    private static final long USE_ARRAY_SIZE = -10L;

    @Ignore
    private final Category$impl<java.lang.Object> 
    $ceylon$language$Category$this;
    @Ignore
    private final Iterable$impl<Element,java.lang.Object> 
    $ceylon$language$Iterable$this;
    @Ignore
    private final Collection$impl<Element> 
    $ceylon$language$Collection$this;
    @Ignore
    private final Correspondence$impl<Integer,Element> 
    $ceylon$language$Correspondence$this;
    @Ignore
    private final List$impl<Element> 
    $ceylon$language$List$this;
    @Ignore
    private final Sequential$impl<Element> 
    $ceylon$language$Sequential$this;
    @Ignore
    private final Sequence$impl<Element> 
    $ceylon$language$Sequence$this;
    
    @Ignore
    @Override
    public Category$impl<java.lang.Object> 
    $ceylon$language$Category$impl(){
        return $ceylon$language$Category$this;
    }

    @Ignore
    @Override
    public Iterable$impl<Element,java.lang.Object> 
    $ceylon$language$Iterable$impl(){
        return $ceylon$language$Iterable$this;
    }

    @Ignore
    @Override
    public Collection$impl<Element> 
    $ceylon$language$Collection$impl(){
        return $ceylon$language$Collection$this;
    }

    @Ignore
    @Override
    public List$impl<Element> $ceylon$language$List$impl(){
        return $ceylon$language$List$this;
    }

    @Ignore
    @Override
    public Correspondence$impl<Integer,Element> 
    $ceylon$language$Correspondence$impl(){
        return $ceylon$language$Correspondence$this;
    }

    @Ignore
    @Override
    public Sequential$impl<Element> 
    $ceylon$language$Sequential$impl(){
        return $ceylon$language$Sequential$this;
    }

    @Ignore
    @Override
    public Sequence$impl<Element> 
    $ceylon$language$Sequence$impl(){
        return $ceylon$language$Sequence$this;
    }

    @Ignore
    @Override
    public boolean getEmpty() {
        return false;
    }
    
    @Ignore
    @Override
    public boolean defines(@Name("key") Integer key) {
        long ind = key.longValue();
        return ind>=0 && ind<array.length || rest.defines(Integer.instance(ind-this.array.length));
    }

    @Ignore
    private class TupleIterator 
            extends BaseIterator<Element> {
    	
        private TupleIterator() {
            super($getReifiedElement$());
        }
        
        private long idx = 0;
        
        private Iterator<Element> restIter = rest.iterator(); 
        
        @Override
        public java.lang.Object next() {
            if (idx < array.length) {
                return array[Util.toInt(idx++)];
            }
            else {
                return restIter.next();
            }
        }
        
        @Override
        public java.lang.String toString() {
            return "TupleIterator";
        }
        
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    @Ignore
    public Sequence<Integer> getKeys() {
        return (Sequence)$ceylon$language$Sequence$this.getKeys();
    }

    @Override
    @Ignore
    public boolean 
    definesEvery(Iterable<? extends Integer, ?> keys) {
        return $ceylon$language$Correspondence$this.definesEvery(keys);
    }

    @Override
    @Ignore
    public boolean 
    definesAny(Iterable<? extends Integer, ?> keys) {
        return $ceylon$language$Correspondence$this.definesAny(keys);
    }
    
    @Override
    @Ignore
    public Sequential<? extends Element> 
    items(Iterable<? extends Integer,?> keys) {
        return $ceylon$language$Correspondence$this.items(keys);
    }

    @Override
    @Ignore
    public java.lang.String toString() {
        return $ceylon$language$Sequence$this.toString();
    }

    @Ignore
    @Override
    public boolean equals(java.lang.Object that) {
        return $ceylon$language$List$this.equals(that);
    }

    @Ignore
    @Override
    public int hashCode() {
        return $ceylon$language$List$this.hashCode();
    }

    @Ignore
    @Override
    public long count(
            @TypeInfo("ceylon.language::Callable<ceylon.language::Boolean,ceylon.language::Tuple<Element,Element,ceylon.language::Empty>>")
            @Name("selecting")@FunctionalParameter("(element)") Callable<? extends Boolean> f) {
        int count=0;
        for (int ii = 0; ii < array.length; ii++) {
            java.lang.Object x = array[ii];
            if (x!=null && f.$call$(x).booleanValue()) count++;
        }
        return count + rest.count(f);
    }

    @Override
    @Ignore
    public boolean containsEvery(Iterable<?,?> elements) {
        return $ceylon$language$Category$this.containsEvery(elements);
    }

    @Override
    @Ignore
    public boolean containsAny(Iterable<?,?> elements) {
        return $ceylon$language$Category$this.containsAny(elements);
    }

    @Override
    @Ignore
    public Iterable<? extends Integer,?> inclusions(List<?> element) {
        return $ceylon$language$List$this.inclusions(element);
    }

    @Override
    @Ignore
    public Integer firstInclusion(List<?> element) {
        return $ceylon$language$List$this.firstInclusion(element);
    }

    @Override
    @Ignore
    public Integer lastInclusion(List<?> element) {
        return $ceylon$language$List$this.lastInclusion(element);
    }

    @Override
    @Ignore
    public Iterable<? extends Integer,?> occurrences(java.lang.Object element) {
        return $ceylon$language$List$this.occurrences(element);
    }

    @Override
    @Ignore
    public Integer firstOccurrence(java.lang.Object element) {
        return $ceylon$language$List$this.firstOccurrence(element);
    }

    @Override
    @Ignore
    public Integer lastOccurrence(java.lang.Object element) {
        return $ceylon$language$List$this.lastOccurrence(element);
    }

    @Override
    @Ignore
    public boolean occurs(java.lang.Object element) {
        return $ceylon$language$List$this.occurs(element);
    }
    
    @Override
    @Ignore
    public boolean occursAt(long index, java.lang.Object element) {
        return $ceylon$language$List$this.occursAt(index, element);
    }

    @Override
    @Ignore
    public boolean includesAt(long index, List<?> element) {
        return $ceylon$language$List$this.includesAt(index, element);
    }
        
    @Override
    @Ignore
    public boolean includes(List<?> element) {
        return $ceylon$language$List$this.includes(element);
    }
        
    @Override
    @Ignore
    public boolean startsWith(List<?> element) {
        return $ceylon$language$List$this.startsWith(element);
    }
        
    @Override
    @Ignore
    public boolean endsWith(List<?> element) {
        return $ceylon$language$List$this.endsWith(element);
    }
    
    @Override
    @Ignore
    public Sequence<? extends Element> sequence() {
        return this;//$ceylon$language$Sequence$this.sequence();
    }

    @Override @Ignore
    public Element find(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.find(f);
    }
    @Override @Ignore
    public Element findLast(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.findLast(f);
    }
    @Override
    @Ignore
    public Sequence<? extends Element> sort(Callable<? extends Comparison> f) {
        return $ceylon$language$Sequence$this.sort(f);
    }

    @Override
    @Ignore
    public <Result> Iterable<? extends Result, ?> 
    map(@Ignore TypeDescriptor $reifiedResult, Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.map($reifiedResult, f);
    }
    @Override
    @Ignore
    public Iterable<? extends Element, ?> 
    filter(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.filter(f);
    }
    @Override
    @Ignore
    public Iterable<? extends Integer, ?> 
    indexesWhere(Callable<? extends Boolean> f) {
        return $ceylon$language$List$this.indexesWhere(f);
    }
    @Override
    @Ignore
    public Integer 
    firstIndexWhere(Callable<? extends Boolean> f) {
        return $ceylon$language$List$this.firstIndexWhere(f);
    }
    @Override
    @Ignore
    public Integer 
    lastIndexWhere(Callable<? extends Boolean> f) {
        return $ceylon$language$List$this.lastIndexWhere(f);
    }
    @Override
    @Ignore
    public <Result> Sequence<? extends Result> 
    collect(@Ignore TypeDescriptor $reifiedResult, 
            Callable<? extends Result> f) {
        return $ceylon$language$Sequence$this.collect($reifiedResult, f);
    }

    @Override
    @Ignore
    public Sequential<? extends Element> 
    select(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.select(f);
    }

    @Override
    @Ignore
    public <Result> Result 
    fold(@Ignore TypeDescriptor $reifiedResult, 
            Result ini, Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.fold($reifiedResult, 
                ini, f);
    }
    
    @Override
    @Ignore
    public <Result> java.lang.Object 
    reduce(@Ignore TypeDescriptor $reifiedResult, 
            Callable<? extends Result> f) {
        return $ceylon$language$Iterable$this.reduce($reifiedResult, f);
    }
    
    @Override @Ignore
    public boolean any(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.any(f);
    }
    @Override @Ignore
    public boolean every(Callable<? extends Boolean> f) {
        return $ceylon$language$Iterable$this.every(f);
    }
    @Override @Ignore
    public boolean longerThan(long length) {
        return this.array.length+rest.getSize() > length;
    }
    @Override @Ignore
    public boolean shorterThan(long length) {
        return this.array.length+rest.getSize() < length;
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    skip(long skip) {
        return $ceylon$language$Iterable$this.skip(skip);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    take(long take) {
        return $ceylon$language$Iterable$this.take(take);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    by(long step) {
        return $ceylon$language$Iterable$this.by(step);
    }
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    getCoalesced() {
        return $ceylon$language$Iterable$this.getCoalesced();
    }
    @Override @Ignore
    public Iterable<? extends Entry<? extends Integer, ? extends Element>, 
            ?> 
    getIndexed() {
        return $ceylon$language$Iterable$this.getIndexed();
    }
    @SuppressWarnings("rawtypes")
    @Override @Ignore 
    public <Other,Absent>Iterable 
    chain(@Ignore TypeDescriptor $reifiedOther, 
            @Ignore TypeDescriptor $reifiedOtherAbsent, 
            Iterable<? extends Other, ? extends Absent> other) {
        return $ceylon$language$Iterable$this.chain($reifiedOther, 
                $reifiedOtherAbsent, other);
    }
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override @Ignore 
    public <Other> Iterable
    follow(@Ignore TypeDescriptor $reifiedOther, Other other) {
        return $ceylon$language$Iterable$this.follow($reifiedOther, other);
    }
    @Override @Ignore
    public <Default>Iterable<?,?> 
    defaultNullElements(@Ignore TypeDescriptor $reifiedDefault, 
            Default defaultValue) {
        return $ceylon$language$Iterable$this.defaultNullElements($reifiedDefault, 
                defaultValue);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Annotations({
        @Annotation("shared"),
        @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Tuple<Element|Other,Other,ceylon.language::Tuple<Element,First,Rest>>")
    public final <Other> Tuple 
    withLeading(@Ignore TypeDescriptor $reifiedOther, @Name("element") Other e) {
        int length = this.array.length;
        java.lang.Object[] array = new java.lang.Object[length+1];
        array[0] = e;
        arraycopy(this.array, 0, array, 1, length);
        return new Tuple(TypeDescriptor.union($reifiedElement,$reifiedOther), array);
    }
    
    @SuppressWarnings({ "rawtypes" })
    @Annotations({
        @Annotation("shared"),
        @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Tuple<Element|Other,First,ceylon.language::Sequence<Element|Other>>")
    public <Other> Tuple 
    withTrailing(@Ignore TypeDescriptor $reifiedOther, @Name("element") Other e) {
        int length = this.array.length;
        java.lang.Object[] array = new java.lang.Object[length+1];
        arraycopy(this.array, 0, array, 0, length);
        array[length] = e;
        return new Tuple(TypeDescriptor.union($reifiedElement,$reifiedOther), array);
    }
    
    @SuppressWarnings({ "rawtypes" })
    @Annotations({
        @Annotation("shared"),
        @Annotation("actual")})
    @Override
    @TypeInfo("ceylon.language::Tuple<Element|Other,First,ceylon.language::Sequential<Element|Other>>")
    public <Other> Tuple
    append(@Ignore TypeDescriptor $reifiedOther, @Name("elements") Iterable<? extends Other, ?> es) {
        Sequential<?> sequence = es.sequence();
        int length = this.array.length;
        java.lang.Object[] array = new java.lang.Object[length+Util.toInt(sequence.getSize())];
        arraycopy(this.array, 0, array, 0, length);
        int ii = length;
        Iterator iter = sequence.iterator();
        java.lang.Object o;
        while (!((o = iter.next()) instanceof Finished)) {
            array[ii++] = o;
        }
        return new Tuple(TypeDescriptor.union($reifiedElement,$reifiedOther), array);
    }

    @Override @Ignore @SuppressWarnings("rawtypes")
    public <Other> Sequence
    prepend(@Ignore TypeDescriptor $reifiedOther, Iterable<? extends Other, ?> es) {
        return $ceylon$language$Sequence$this.prepend($reifiedOther, es);
    }

    @Override @Ignore @SuppressWarnings("rawtypes")
    public <Other> List
    extend(@Ignore TypeDescriptor $reifiedOther, List<? extends Other> list) {
        return $ceylon$language$List$this.extend($reifiedOther, list);
    }

    @Override @Ignore @SuppressWarnings("rawtypes")
    public <Other> List
    patch(@Ignore TypeDescriptor $reifiedOther, List<? extends Other> list, long from, long length) {
        return $ceylon$language$List$this.patch($reifiedOther, list, from, length);
    }
    
    @Override @Ignore @SuppressWarnings("rawtypes")
    public <Other> List
    patch(@Ignore TypeDescriptor $reifiedOther, List<? extends Other> list, long from) {
        return $ceylon$language$List$this.patch($reifiedOther, list, from, 0);
    }

    @Override @Ignore 
    public <Other> long patch$length(TypeDescriptor $reifiedOther,List<? extends Other> list, long from) {
    	return 0;
    }

    @Override @Ignore
    public Sequential<? extends Element> 
    trim(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trim(characters);
    }

    @Override @Ignore
    public Sequential<? extends Element> 
    trimLeading(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trimLeading(characters);
    }

    @Override @Ignore
    public Sequential<? extends Element> 
    trimTrailing(Callable<? extends Boolean> characters) {
        return $ceylon$language$Sequential$this.trimTrailing(characters);
    }
    
    @Override @Ignore
    public Sequential<? extends Element> 
    initial(long length) {
        return $ceylon$language$Sequential$this.initial(length);
    }
    
    @Override @Ignore
    public Sequential<? extends Element> terminal(long length) {
        return $ceylon$language$Sequential$this.terminal(length);
    }
    
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    takeWhile(Callable<? extends Boolean> take) {
        return $ceylon$language$Iterable$this.takeWhile(take);
    }
    
    @Override @Ignore
    public Iterable<? extends Element, ?> 
    skipWhile(Callable<? extends Boolean> skip) {
        return $ceylon$language$Iterable$this.skipWhile(skip);
    }
    
    @Override
    @Ignore
    public Iterable<? extends Element,?> getCycled() {
        return $ceylon$language$Iterable$this.getCycled();
    }

    @Override
    @Ignore
    public Iterable<? extends Element,?> cycle(long times) {
        return $ceylon$language$Iterable$this.cycle(times);
    }
    
    @Override
    @Ignore
    public Sequential<? extends Element> repeat(long times) {
        return $ceylon$language$Sequential$this.repeat(times);
    }
    
    /** Gets the underlying array. Used for iteration using a C-style for */
    @Ignore
    public java.lang.Object[] $getArray$() {
        if (rest instanceof Empty) {
            return array;
        } 
        return null;
    }
    
    /** Gets the underlying first index. Used for iteration using a C-style for */
    @Ignore
    public int $getFirst$() {
        return 0;
    }
    
    /** Gets the underlying length. Used for iteration using a C-style for */
    @Ignore
    public int $getLength$() {
        return Util.toInt(array.length + rest.getSize());
    }

    @Override @Ignore
    public final <Result,Args extends Sequential<? extends java.lang.Object>> Callable<? extends Iterable<? extends Result, ?>>
    spread(TypeDescriptor $reifiedResult,TypeDescriptor $reifiedArgs, Callable<? extends Callable<? extends Result>> method) {
    	return $ceylon$language$Iterable$this.spread($reifiedResult, $reifiedArgs, method);
    }
    
}