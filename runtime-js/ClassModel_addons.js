//Addendum to ClassModel
atr$(ClassModel$meta$model.$$.prototype,'parameterTypes',function(){
  var ps=this.tipo.$crtmm$.$ps;
  if (!ps || ps.length==0)return getEmpty();
  var r=[];
  for (var i=0; i < ps.length; i++) {
    var pt=ps[i].$t;
    if (typeof(pt)==='string'){
      if (!this.$targs)throw TypeApplicationException$meta$model("This class model needs type parameters");
      pt=this.$targs[pt];
      if (!pt)throw TypeApplicationException$meta$model("Class model is missing type argument for <" + ps[i].$t + ">");
    }
    r.push(typeLiteral$meta({Type$typeLiteral:pt}));
  }
  return r.length===0?getEmpty():ArraySequence(r,{Element$ArraySequence:{t:Type$meta$model,a:{t:Anything}}});
},undefined,function(){return{mod:$CCMM$,$cont:ClassModel$meta$model,d:['ceylon.language.meta.model','ClassModel','$at','parameterTypes'],$t:{t:Sequential,a:{ElementSequential:{t:Type$meta$model,a:{Type:{t:Anything}}},Absent:{t:Null}}}};});

atr$(ClassModel$meta$model.$$.prototype,'declaration',function(){
  var $$clase=this;
  if ($$clase._decl)return $$clase._decl;
  var mm = getrtmm$$($$clase.tipo);
  $$clase._decl = OpenClass(getModules$meta().find(mm.mod['$mod-name'],mm.mod['$mod-version']).findPackage(mm.d[0]), $$clase.tipo);
  return $$clase._decl;
},undefined,function(){return{mod:$CCMM$,$t:{t:ClassDeclaration$meta$declaration},$cont:ClassModel$meta$model,$an:function(){return[shared(),actual()];},d:['ceylon.language.meta.model','ClassModel','$at','declaration']};});
ClassModel$meta$model.$$.prototype.equals=function(o){
return is$(o,{t:AppliedClass}) && o.tipo===this.tipo && this.typeArguments.equals(o.typeArguments);
};
ClassModel$meta$model.$$.prototype.equals.$crtmm$=function(){return{
  mod:$CCMM$,d:['$','Object','$m','equals'],$t:{t:$_Boolean},$ps:[{$nm:'other',$t:{t:$_Object},$mt:'prm'}]
}};
