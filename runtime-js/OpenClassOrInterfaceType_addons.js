defineAttr(OpenClassOrInterfaceType$meta$declaration.$$.prototype,'typeArguments',function(){
  var tps=this.declaration.tipo.$$metamodel$$.$tp;
  if (tps) {
    var rtps = this.declaration._targs;
    var targs=[];
    for (var tpn in tps) {
      var rtp=rtps&&rtps[tpn];
      var otp=OpenTypeParam(this.declaration.tipo,tpn);
      var targ;
      if (typeof(tp)==='string' || rtp===undefined) {
        targ = new OpenTvar(otp);
      } else {
        targ = _openTypeFromTarg(rtp);
      }
      targs.push(Entry(otp, targ, {Key:{t:TypeParameter$meta$declaration},Item:{t:OpenType$meta$declaration}}));
    }
    return LazyMap(targs.reifyCeylonType({Absent:{t:Null},Element:{t:Entry,a:{Key:{t:TypeParameter$meta$declaration},Item:{t:OpenType$meta$declaration}}}}),{Key:{t:TypeParameter$meta$declaration},Item:{t:OpenType$meta$declaration}});
  }
  return getEmpty();
},undefined,function(){return{mod:$$METAMODEL$$,$t:{t:Map,a:{Key:{t:TypeParameter$meta$declaration},Item:{t:OpenType$meta$declaration}}},$cont:OpenClassOrInterfaceType,$an:function(){return[shared(),actual()];},d:['ceylon.language.meta.declaration','OpenClassOrInterfaceType','$at','typeArguments']};});
defineAttr(OpenClassOrInterfaceType$meta$declaration.$$.prototype,'extendedType',function(){
  return this.declaration.extendedType;
},undefined,function(){return{mod:$$METAMODEL$$,$t:{ t:'u', l:[{t:Null},{t:OpenClassType$meta$declaration}]},$cont:OpenClassOrInterfaceType,$an:function(){return[shared(),actual()];},d:['ceylon.language.meta.declaration','OpenClassOrInterfaceType','$at','extendedType']};});
defineAttr(OpenClassOrInterfaceType$meta$declaration.$$.prototype,'declaration',function(){return this._decl;},undefined,function(){return{mod:$$METAMODEL$$,$t:{t:ClassDeclaration$meta$declaration},$cont:OpenClassOrInterfaceType,$an:function(){return[shared(),actual()];},d:['ceylon.language.meta.declaration','OpenClassOrInterfaceType','$at','declaration']};});
defineAttr(OpenClassOrInterfaceType$meta$declaration.$$.prototype,'satisfiedTypes',function(){
  return this.declaration.satisfiedTypes;
},undefined,function(){return{mod:$$METAMODEL$$,$t:{t:Sequential,a:{Element:{t:OpenInterfaceType$meta$declaration}}},$cont:OpenClassOrInterfaceType$meta$declaration,$an:function(){return[shared(),actual()];},d:['ceylon.language.meta.declaration','OpenClassOrInterfaceType','$at','satisfiedTypes']};});
defineAttr(OpenClassOrInterfaceType$meta$declaration.$$.prototype,'string',function(){
  var s=this.declaration.string;
  if (this.declaration._targs) {
    s+="<PENDIENTE TARGS>";
  } else if (this.declaration.tipo.$$metamodel$$.$tp) {
    s+="<PEND TPARAMS>";
  }
  return s;
},undefined,function(){return{mod:$$METAMODEL$$,$t:{t:String$},d:['ceylon.language','Object','$at','string']};});
