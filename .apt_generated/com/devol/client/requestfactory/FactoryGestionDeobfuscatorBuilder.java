// Automatically Generated -- DO NOT EDIT
// com.devol.client.requestfactory.FactoryGestion
package com.devol.client.requestfactory;
import java.util.Arrays;
import com.google.web.bindery.requestfactory.vm.impl.OperationData;
import com.google.web.bindery.requestfactory.vm.impl.OperationKey;
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
withRawTypeToken("T_ExC62NfDxfVR_wpuxy_D5b4pM=", "com.devol.client.beanproxy.AmortizacionProxy");
withRawTypeToken("zYsKyaSFf1NsRKdZ_b1Wt7ItZ4o=", "com.devol.client.beanproxy.ClienteProxy");
withRawTypeToken("GnXoOePEyNDniV3pqQ0RNRg0YS4=", "com.devol.client.beanproxy.PrestamoProxy");
withRawTypeToken("YPubXVP69AyEwl6hiTuaJeinPGs=", "com.devol.client.beanproxy.UsuarioProxy");
withRawTypeToken("w1Qg$YHpDaNcHrR5HZ$23y518nA=", "com.google.web.bindery.requestfactory.shared.EntityProxy");
withRawTypeToken("FXHD5YU0TiUl3uBaepdkYaowx9k=", "com.google.web.bindery.requestfactory.shared.BaseProxy");
withClientToDomainMappings("com.devol.server.model.bean.Amortizacion", Arrays.asList("com.devol.client.beanproxy.AmortizacionProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Cliente", Arrays.asList("com.devol.client.beanproxy.ClienteProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Prestamo", Arrays.asList("com.devol.client.beanproxy.PrestamoProxy"));
withClientToDomainMappings("com.devol.server.model.bean.Usuario", Arrays.asList("com.devol.client.beanproxy.UsuarioProxy"));
}}
