// Automatically Generated -- DO NOT EDIT
// com.devol.client.requestfactory.FactoryGestion
package com.devol.client.requestfactory;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
import com.google.gwt.core.shared.GwtIncompatible;
@GwtIncompatible("Server-side only but loaded through naming convention so must be in same package as shared FactoryGestion interface")
public final class FactoryGestionDeobfuscatorBuilder extends com.google.web.bindery.requestfactory.vm.impl.Deobfuscator.Builder {
{
withOperation(new OperationKey("RLZkfE_SFCacjsaAOBCtedZfRSw="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;Ljava/util/Date;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;Ljava/util/Date;)Ljava/util/List;")
  .withMethodName("listarAmortizacionByDate")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("GFo180blB1rL_mZkxNuPDw9tl04="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/PrestamoProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Prestamo;)Ljava/lang/Boolean;")
  .withMethodName("insertarPrestamo")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("NiJ8CN4PtvDUtPhUFzv9GDV$Foc="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarPrestamoByUsuario")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("WM2qS2JQhRfnOqbmaIzxx46FLiI="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/PrestamoProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Prestamo;)Ljava/lang/Boolean;")
  .withMethodName("eliminarPrestamo")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("CvjW$ldczCvgMHMVVLDKs9$WmC8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/PrestamoProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Prestamo;)Ljava/lang/Boolean;")
  .withMethodName("actualizarPrestamo")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("Nu1SEHNQy7QOPYE4ZkCmhbjg4T0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/AmortizacionProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Amortizacion;)Ljava/lang/Boolean;")
  .withMethodName("insertarAmortizacion")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("08JKgsizdOCaD8m2sqIG8qjS820="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/AmortizacionProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Amortizacion;)Ljava/lang/Boolean;")
  .withMethodName("eliminarAmortizacion")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("VIIATzryIx4cULpU3ieS6sIfgTE="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/lang/Boolean;")
  .withMethodName("sendPrestamoHistorial")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("mwnTL7GDyo5rlJEt$N9KRQ3Sv5k="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("listarPrestamo")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("IFPP3yAc4KkQAFJsu6ssi6YZlgY="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarAmortizacionByPrestamo")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionPrestamo")
  .build());
withOperation(new OperationKey("b5RhVGh_Pjmk8J1U9kUkJuWKdeg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/lang/Boolean;")
  .withMethodName("recuperarClave")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionUsuario")
  .build());
withOperation(new OperationKey("gb5OUKx$slDsk$M4KkhPQUITen8="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Lcom/devol/server/model/bean/Usuario;")
  .withMethodName("existeCuenta")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionUsuario")
  .build());
withOperation(new OperationKey("CxU1DaFnzPM3nzYGS499MvIbIZA="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/UsuarioProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Usuario;)Ljava/lang/Boolean;")
  .withMethodName("insertarUsuario")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionUsuario")
  .build());
withOperation(new OperationKey("COl5vbRKz5QlOa9Mb68fxRPbcPc="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/ClienteProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Cliente;)Ljava/lang/Boolean;")
  .withMethodName("actualizarCliente")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCliente")
  .build());
withOperation(new OperationKey("kyTWHsKEJyvtPPKg4ega$CTyIqA="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/ClienteProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Cliente;)Ljava/lang/Boolean;")
  .withMethodName("insertarCliente")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCliente")
  .build());
withOperation(new OperationKey("9h0GjTKkgNN3siANAUHMBgQ47aA="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/ClienteProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/Cliente;)Ljava/lang/Boolean;")
  .withMethodName("eliminarCliente")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCliente")
  .build());
withOperation(new OperationKey("V2jup817BEORJOrJCCOMxTA2nRQ="),
  new OperationData.Builder()
  .withClientMethodDescriptor("()Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("()Ljava/util/List;")
  .withMethodName("listarCliente")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCliente")
  .build());
withOperation(new OperationKey("xuOz20FFNSlMyskVaD5MTPREaCg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarClienteByUsuario")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCliente")
  .build());
withOperation(new OperationKey("r8Uj55IuIFx7mMrn_9wGioTF7Qg="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarPrestamistaByCobrador")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("Wg$ejygmqYiEvx2TFDEZ71BUmDo="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/lang/Boolean;")
  .withMethodName("desactivarGestorCliente")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("xL5RouHjCDQNm_fpnqrDneZUsWY="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Lcom/devol/client/beanproxy/GestorCobranzaProxy;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Lcom/devol/server/model/bean/GestorCobranza;)Ljava/lang/Boolean;")
  .withMethodName("insertarCobrador")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("UjpyLTaQTqzEjyu6j_VjU2BzIAU="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarGestorCobranzaByUsuario")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("ytEtDcCcejOyE9P_DT$t5gBPhWc="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/lang/Boolean;")
  .withMethodName("desactivarGestorCobranza")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("02QfR7SualkmDszycodcdnxt1y0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarGestorClienteByCobrador")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("NB6eIwhctKlB6ng7GolGZiCwnC0="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/util/Set;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Boolean;")
  .withMethodName("asignarClientesAlCobrador")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withOperation(new OperationKey("MTr3zy$$3KTDaVL2SgaXEpjHabs="),
  new OperationData.Builder()
  .withClientMethodDescriptor("(Ljava/lang/String;)Lcom/google/web/bindery/requestfactory/shared/Request;")
  .withDomainMethodDescriptor("(Ljava/lang/String;)Ljava/util/List;")
  .withMethodName("listarClienteSinCobrador")
  .withRequestContext("com.devol.client.requestfactory.ContextGestionCobranza")
  .build());
withRawTypeToken("T_ExC62NfDxfVR_wpuxy_D5b4pM=", "com.devol.client.beanproxy.AmortizacionProxy");
withRawTypeToken("zYsKyaSFf1NsRKdZ_b1Wt7ItZ4o=", "com.devol.client.beanproxy.ClienteProxy");
withRawTypeToken("phE6kpqtrafPxKdEeV0rE1ztMGI=", "com.devol.client.beanproxy.GestorCobranzaProxy");
withRawTypeToken("GnXoOePEyNDniV3pqQ0RNRg0YS4=", "com.devol.client.beanproxy.PrestamoProxy");
withRawTypeToken("YPubXVP69AyEwl6hiTuaJeinPGs=", "com.devol.client.beanproxy.UsuarioProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("com.devol.server.model.bean.Amortizacion", Arrays.asList("com.devol.client.beanproxy.AmortizacionProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Cliente", Arrays.asList("com.devol.client.beanproxy.ClienteProxy"));
withClientToDomainMappings("com.devol.server.model.bean.GestorCobranza", Arrays.asList("com.devol.client.beanproxy.GestorCobranzaProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Prestamo", Arrays.asList("com.devol.client.beanproxy.PrestamoProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Usuario", Arrays.asList("com.devol.client.beanproxy.UsuarioProxy"));
}}
