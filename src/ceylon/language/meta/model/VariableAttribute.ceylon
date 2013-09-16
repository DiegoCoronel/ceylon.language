import ceylon.language.meta.declaration { VariableDeclaration }

shared interface VariableAttribute<in Container, Type>
        satisfies Member<Container, Variable<Type>> & Attribute<Container, Type> {
    
    shared formal actual VariableDeclaration declaration;
}