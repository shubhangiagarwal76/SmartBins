$wnd.showcase.runAsyncCallback18("function lA(){}\nfunction CJb(a,b){$w(a.a,b)}\nfunction bgb(a,b){this.b=a;this.a=b}\nfunction dgb(a,b){this.b=a;this.a=b}\nfunction LA(a){return AY(wA,a)}\nfunction kA(){kA=MX;jA=new lA}\nfunction Xfb(a,b){NCb(b,'\\u5DF2\\u9009\\u62E9: '+a.Vf()+', '+a.Wf())}\nfunction OJb(){KJb();NJb.call(this,Mo($doc,'password'),'gwt-PasswordTextBox')}\nfunction sRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;return c.text.length}catch(a){return 0}}\nfunction rRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;return -c.move(rcc,-65535)}catch(a){return 0}}\nfunction Vfb(a,b){var c,d;c=new GGb;c.e[yac]=4;DGb(c,a);if(b){d=new RCb('\\u5DF2\\u9009\\u62E9: 0, 0');Hh(a,new bgb(a,d),(iu(),iu(),hu));Hh(a,new dgb(a,d),(Nt(),Nt(),Mt));DGb(c,d)}return c}\nfunction uRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return 0;var d=c.text.length;var e=0;var f=c.duplicate();f.moveEnd(rcc,-1);var g=f.text.length;while(g==d&&f.parentElement()==b&&c.compareEndPoints('StartToEnd',f)<=0){e+=2;f.moveEnd(rcc,-1);g=f.text.length}return d+e}catch(a){return 0}}\nfunction tRb(b){try{var c=b.document.selection.createRange();if(c.parentElement()!==b)return -1;var d=c.duplicate();d.moveToElementText(b);d.setEndPoint('EndToStart',c);var e=d.text.length;var f=0;var g=d.duplicate();g.moveEnd(rcc,-1);var h=g.text.length;while(h==e&&g.parentElement()==b){f+=2;g.moveEnd(rcc,-1);h=g.text.length}return e+f}catch(a){return 0}}\nfunction Wfb(){var a,b,c,d,e,f;f=new kQb;f.e[yac]=5;d=new MJb;VPb(d.hb,'','cwBasicText-textbox');CJb(d,(kA(),kA(),jA));b=new MJb;VPb(b.hb,'','cwBasicText-textbox-disabled');b.hb[pbc]=pcc;Zw(b.a);b.hb[p9b]=true;hQb(f,new WCb('<b>\\u5E38\\u89C4\\u6587\\u672C\\u6846:<\\/b>'));hQb(f,Vfb(d,true));hQb(f,Vfb(b,false));c=new OJb;VPb(c.hb,'','cwBasicText-password');a=new OJb;VPb(a.hb,'','cwBasicText-password-disabled');a.hb[pbc]=pcc;Zw(a.a);a.hb[p9b]=true;hQb(f,new WCb('<br><br><b>\\u5BC6\\u7801\\u6587\\u672C\\u6846:<\\/b>'));hQb(f,Vfb(c,true));hQb(f,Vfb(a,false));e=new oOb;VPb(e.hb,'','cwBasicText-textarea');e.hb.rows=5;hQb(f,new WCb('<br><br><b>\\u6587\\u672C\\u533A\\u57DF:<\\/b>'));hQb(f,Vfb(e,true));return f}\nvar pcc='\\u53EA\\u8BFB',rcc='character';LX(867,1136,{},lA);_.qd=function mA(a){return LA((FA(),a))?(hz(),gz):(hz(),fz)};var jA;var rI=NWb(P7b,'AnyRtlDirectionEstimator',867);LX(439,1,b9b);_.Bc=function agb(){d$(this.a,Wfb())};LX(440,1,qcc,bgb);_.Uc=function cgb(a){Xfb(this.b,this.a)};var PN=NWb(m9b,'CwBasicText/2',440);LX(441,1,T8b,dgb);_.Sc=function egb(a){Xfb(this.b,this.a)};var QN=NWb(m9b,'CwBasicText/3',441);LX(746,246,V6b);_.Vf=function FJb(){return rRb(this.hb)};_.Wf=function GJb(){return sRb(this.hb)};LX(325,50,V6b,OJb);var IS=NWb(T6b,'PasswordTextBox',325);LX(215,311,V6b);_.Vf=function pOb(){return tRb(this.hb)};_.Wf=function qOb(){return uRb(this.hb)};d6b(wl)(18);\n//# sourceURL=showcase-18.js\n")
