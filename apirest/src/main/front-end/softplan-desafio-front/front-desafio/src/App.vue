<!--Template contendo as duas páginas Usuário e Cadatro e suas funções javascript do Vue.js e estilos -->
<template>

  <div>
    
    <nav>
      <div class="nav-wrapper blue darken-1">   
        <span class="brand-logo center titleNav">Desafio Softplan </span>   
        <div v-if="this.usuarioLogin.cd_usuario" class="linkProd">           
           <a href="#" @click.prevent="linkPro()" class="">{{linkProd}}</a>  
           <i class="material-icons">link</i>        
        </div>
        <div>
             <div style="margin-left:20px;">
              <input minlength="3" style="width:300px;background:white;" type="text" placeholder="Digite Email de Login" v-model="emailLogin">
              <button @click="buscarUsuarioLogin()" class="btn-small blue btSearch">
                <i class="material-icons center iconSearch">account_circle</i>
              </button>
              {{this.usuarioLogin.perfilUsuario}}
            </div>
        </div>             
      </div>      
    </nav>

    <div class="container">
      <span v-if="!this.usuarioLogin.cd_usuario" class="pageIni">Desafio SoftPlan FullStack</span>
      <div v-if="this.usuarioLogin.cd_usuario">
      <!--Pagina da aplicação associada ao Cadastro de usuário -->
      <div id="div-user" v-if="this.linkProd==='Processos'" style="display:">  
          <div>         
            <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" v-on:click="showModal('modalUser')" data-target="modalUser" class="modal-trigger addUser"><i class="large material-icons iconUser">account_circle</i>+[{{title}}]</button>
            <div class="divSearch">              
              <input minlength="3" style="width:300px" type="text" placeholder="Busca" v-model="searchField">
              <button @click="pesquisarUsuario()" class="btn-small blue btSearch"><i class="material-icons center iconSearch">search</i></button>              
            </div>
          </div>      
          <div id="modalUser" class="modal modalUser" v-if="this.usuarioLogin.perfilUsuario==='ADMIN'">
            <div class="modal-content">
                  <h5>Cadastro de Usuário</h5>
                  <ul>
                    <li v-for="(erro,index) of errors" :key="index">
                      <b>{{erro}}</b> 
                    </li>
                  </ul>
                  <form @submit.prevent="salvarUsuario" v-if="this.usuarioLogin.perfilUsuario==='ADMIN'">
                    <label>Nome:</label>
                    <input minlength="3" type="text" placeholder="Nome" v-model="usuario.nome">
                    <label>Email:</label>
                    <input minlength="8" type="email" placeholder="Email" v-model="usuario.email">
                    <label>Tipo de Usuário:</label>
                    <select style="display:initial" id='perfilUsuario' placeholder="PerfilUsuario" v-model="usuario.perfilUsuario">
                      <option selected value="DISTRIBUIDOR">DISTRIBUIDOR</option>
                      <option value="FINALIZADOR">FINALIZADOR</option>
                    </select>
                    <div id='status' style="display:none">
                      <label>Status:</label>
                      <select style="display:initial" placeholder="status" v-model="usuario.status">
                        <option value="true">true</option>
                        <option value="false">false</option>
                      </select>
                    </div>
                    <br><br>
                    <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" class="btn-small blue ">Salvar<i class="material-icons left">save</i></button>
                    <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" type="button" v-on:click="closeModal('modalUser')" class="btn-small blue ">Cancelar<i class="material-icons left">cancel</i></button>
                  </form>
            </div>        
          </div> 
          <div style="display:block;position:relative;top:30px;">
          <center><label class="titleTab">Lista de Usuários</label></center>       
          <table>
            <thead>
              <tr>
                <th>Nome</th>
                <th>Email</th>
                <th>Perfil</th>
                <th>Status</th>
                <th>Dt Cadastro</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="usuario of usuarios" :key="usuario.cd_usuario">
                <td>{{usuario.nome}}</td>
                <td>{{usuario.email}}</td>
                <td>{{usuario.perfilUsuario}}</td>
                <td v-if="usuario.status===true">Ativo</td><td v-if="usuario.status===false">Desativado</td> 
                <td>{{usuario.dt_cadastro}}</td>
                <td>
                  <button v-if="usuarioLogin.perfilUsuario==='ADMIN'" @click="editarUsuario(usuario)" class="btn-small blue"><i class="material-icons">create</i></button>
                </td>
              </tr>
            </tbody>      
          </table>
          </div>
      </div> 

      <!--Pagina da aplicação associada ao Cadastro de Processos -->
      <div id="div-process" v-if="this.linkProd==='Usuários'" style="display:" >  
          <div>
            <div style="float:left;">   
              <button v-if="this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'" v-on:click="showModal('modalProcesso')" data-target="modalProcesso" class="modal-trigger addProcess"><i class="large material-icons iconProcess">book</i>+[{{title}}]</button>                 
            </div>      
            <div id="filtro_processo" style="float:right;">   
                <br>         
                <label><input name="statusProcesso" v-on:click="atualizarListaProcesso(false)" type="radio" value="false" checked /><span>Pendentes</span></label>
                <label><input name="statusProcesso" v-on:click="atualizarListaProcesso(true)" type="radio" value="true" /><span>Finalizados</span></label>            
            </div>
          </div>
          <div v-if="this.usuarioLogin.perfilUsuario!=='ADMIN'" id="modalProcesso" class="modal modalProcesso">
            <div class="modal-content">
              <div id="cadastroProcesso">
                  <h5>Cadastro de Processo</h5>
                  <ul>
                    <li v-for="(erro,index) of errors" :key="index">
                      <b>{{erro}}</b> 
                    </li>
                  </ul>    
                  <form @submit.prevent="salvarProcesso">
                    <div v-if="this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'">
                        <label>Numero Processo:</label>
                        <input minlength="1" type="text" placeholder="Numero Processo" v-model="processo.num_processo">
                        <label>Descrição:</label>
                        <input minlength="10" type="text" placeholder="Descrição" v-model="processo.ds_processo">
                        <label>Usuário FInaliza:</label>
                        <select v-model="selectedUsuarioPerfil" name="usuarioFinaliza" style="display:initial !important" placeholder="Usuario Finaliza" id="usuarioFinaliza" >
                          <option disabled value="" selected="selected">Please select one</option>
                          <option v-for="(usuarioPerfil,index) in usuariosPerfil" 
                                      :key="index" 
                                      :value="usuarioPerfil.cd_usuario">Nome:{{usuarioPerfil.nome}}     -    Email:{{ usuarioPerfil.email }}</option>
                        </select> 
                    </div>      
                    <div v-if="this.usuarioLogin.perfilUsuario==='FINALIZADOR'">                         
                        <label>Num Processo:</label>
                        <span>{{processo.num_processo}}</span><br>
                        <label>Distribuidor:</label>
                        <span>{{processo.usuarioCadastro.nome}}</span><br>
                        <label>Descrição:</label>
                        <span>{{processo.ds_processo}}</span><br>
                        <hr>
                        <label>Parecer:</label>
                        <textarea placeholder="Parecer" v-model="processo.parecer"></textarea>
                        <label>Status:</label>
                        <select style="display:initial" id='status_finalizado' placeholder="Status Parecer" v-model="processo.status_finalizado">
                          <option value="true">Finalizado</option>
                          <option selected value="false">Aberto (em análise)</option>
                        </select>     
                    </div>                        
                    <br><br>
                    <button class="btn-small blue">Salvar<i class="material-icons left">save</i></button>
                    <button type="button" v-on:click="closeModal('modalProcesso')" class="btn-small blue ">Cancelar<i class="material-icons left">cancel</i></button>
                  </form>
               </div>
            </div>        
          </div>  
          <br><br><br><br>            
          <center><label class="titleTab">Lista de Processos</label></center>   
          <table>
            <thead>
              <tr>
                <th>Numero</th>
                <th>Descrição</th>                
                <th>Status Parecer</th> 
                <th>Dt Processo</th>
                <th>Dt Parecer</th>
                <th>Parecer</th>
                <th>Distribuidor</th>
                <th v-if="usuarioLogin.perfilUsuario!=='FINALIZADOR'">Finalizador</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="processo of processos" :key="processo.cd_processo">
                  <td>{{processo.num_processo}}</td>
                  <td>{{processo.ds_processo}}</td>                
                  <td v-if="processo.status_finalizado===true">Finalizado</td><td v-if="processo.status_finalizado===false">Aberto</td>                
                  <td v-if="processo.dt_processo">{{moment(processo.dt_processo).format('DD/MM/YYYY HH:mm:SS')}}</td><td v-else></td>
                  <td v-if="processo.dt_parecer">{{moment(processo.dt_parecer).format('DD/MM/YYYY HH:mm:SS')}}</td><td v-else></td>
                  <td>{{processo.parecer}}</td>
                  <td>{{processo.usuarioCadastro.nome}}</td>
                  <td v-if="usuarioLogin.perfilUsuario!=='FINALIZADOR'">{{processo.usuarioFinaliza.nome}}</td>
                  <td>
                    <button v-if="usuarioLogin.perfilUsuario==='FINALIZADOR' && processo.status_finalizado===false" @click="editarProcesso(processo)" class="btn-small blue"><i class="material-icons">create</i></button>
                </td>
              </tr>
            </tbody>      
          </table>
      </div> 
    </div>
    </div>

  </div>
</template>


<script>


import Usuario from './services/usuarios'
import Processo from './services/processos'

import M from 'materialize-css'

export default {

  data(){
     return{       
       usuario:{
         cd_usuario:'',
         nome:'',
         email:'',
         status:'',
         perfilUsuario:'',
         dt_cadastro:''
       },       
       usuarioLogin:{
         cd_usuario:'',
         nome:'',
         email:'',
         status:'',
         perfilUsuario:'',
         dt_cadastro:''
       },
       processo:{
         cd_processo:'',
         num_processo:'',
         ds_processo:'',
         status_finalizado:false,
         parecer:'',
         dt_processo:'',
         dt_parecer:'',
         usuarioCadastro:{},
         usuarioFinaliza:{}
       },
       selectedUsuarioPerfil:'',
       usuarios: [],
       processos:[],
       usuariosPerfil:[],
       errors: [],
       searchField:'',
       emailLogin:'',
       title:'Cadastro de Usuário',
       linkProd:'Processos',
       statusProcesso:false
     }
  },

  mounted(){
    M.AutoInit()
    this.listarUsuario();      
  },
  
  
  methods: {    

       /* Método para Alterar de Cadastro de usuário para de Processos */
      linkPro(){
        this.searchField='';
        if(this.linkProd==='Processos'){
          this.linkProd='Usuários';
          this.title='Cadastro de Processos';
          this.usuarios=[];
          this.usuario=[];
          this.statusProcesso=false;
          this.listarProcesso();         
        }else{
          this.linkProd='Processos';
          this.title='Cadastro de Usuários';
          this.processos=[];      
          this.processo=[];   
          this.listarUsuario();         
        }
      },

      showModal: function(id) {   
        document.getElementById(id).style.display='initial'
        if(this.linkProd==='Processos'){
          if(!this.usuario.cd_usuario){
            document.getElementById('status').style.display='none'
            this.usuario={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.errors=[]
          }else{
            document.getElementById('status').style.display='initial'
          }  
        }else{
          if(!this.processo.cd_processo){
            this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_processo:'',dt_parecer:'',usuarioCadastro:{},usuarioFinaliza:{}}
            this.errors=[]
          }
        }
      },

      closeModal: function(id) {
        if(this.linkProd==='Processos'){
            this.usuario={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
        }else{
          this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_processo:'',dt_parecer:'',usuarioCadastro:{},usuarioFinaliza:{}}
        }
        this.errors=[]
        document.getElementById(id).style.display='none'
      },

      callMessage: function(txt){
        M.toast({html: txt, classes: 'toastApi'})
      },

          /*
          * Métodos Listar, Pesquisar, salvar e atualizar associados com a 
          * entidade Usuário e os serviços Spring Rest
          */   
          buscarUsuarioLogin(){
            this.usuario={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.usuarioLogin={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.usuariosPerfil={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_processo:'',dt_parecer:'',usuarioCadastro:{},usuarioFinaliza:{}}
            this.selectedUsuarioPerfil=''
            this.errors=[]
            this.searchField=''
            this.title='Cadastro de Usuário'
            this.linkProd='Processos'
            this.errors=[]
            this.processos=[]
            this.usuarios=[]
            this.statusProcesso=false            
            Usuario.buscarUsuarioLogin(this.emailLogin).then(resposta => {
              this.usuarioLogin = resposta.data
              this.listarUsuario()
              if(this.usuarioLogin.cd_usuario){
                this.callMessage('Logado com Sucesso:' || this.usuarioLogin.nome)
              }else{
                this.callMessage('Erro: Verifique dados de Login')
              }
            })            
          },
          
          listarUsuario(){
            if(this.usuarioLogin.cd_usuario>0){
              Usuario.listarUsuario().then(resposta => {
                this.usuarios = resposta.data
              })
            }
          },
          
          validaEmail: function (email) {
            var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
          },
          

          salvarUsuario(){
            
              this.errors = []
              
              if (!this.usuario.nome) {
                this.errors.push('Nome é obrigatório');
              }        
              if (this.validaEmail(this.usuario.email)===false) {
                this.errors.push('Verifique o campo Email');
              }
              if (!this.usuario.perfilUsuario) {
                this.errors.push('Perfil é obrigatório');
              }        

              if(this.errors.length===0){
                  if(!this.usuario.cd_usuario){
                    document.getElementById('status').style.display='none'
                    Usuario.salvarUsuario(this.usuario).then(response => {
                      response
                      this.usuario={}
                      this.callMessage('Salvo com sucesso!' || this.usuarioLogin.nome)
                      this.listarUsuario()
                      this.closeModal('modalUser')
                    }).catch(error => {
                        console.log(error)
                    })
                  }else{ 
                    this.errors=[]
                    document.getElementById('status').style.display='initial'
                    Usuario.atualizarUsuario(this.usuario).then(resposta => {
                      resposta
                      this.usuario={}            
                      this.callMessage('Atualizado com sucesso!' || this.usuarioLogin.nome)
                      this.listarUsuario()
                      this.closeModal('modalUser')
                    }).catch((error) => {
                        console.log(error)
                    })
                  }
              }

            },

            editarUsuario(usuario){
              this.showModal('modalUser')  
              document.getElementById('status').style.display='initial'      
              this.usuario=usuario
            },

            pesquisarUsuario(){
              Usuario.pesquisarUsuario(this.searchField).then(resposta => {
                this.usuarios = resposta.data
              })

            },    


            /*
            *  Métodos Listar, Pesquisar, salvar e atualizar associados com a entidade 
            *  Processo e os serviços Spring Boot JPA / Rest
            */

            atualizarListaProcesso(status_){
              this.statusProcesso=status_;
              this.listarProcesso();
            },

            listarProcesso(){
              this.processos=[];
              if(this.usuarioLogin.perfilUsuario==='FINALIZADOR'){                  
                Processo.pesquisarProcessoFinalizador(this.usuarioLogin.email,this.statusProcesso).then(resposta => {
                  this.processos = resposta.data
                })
              }else{
                Processo.pesquisarProcessoStatus(this.statusProcesso).then(resposta => {
                  this.processos = resposta.data
                })
                Usuario.listaUsuarioPerfil('FINALIZADOR').then(resposta => {
                  this.usuariosPerfil = resposta.data
                })
              }  
            },          

            salvarProcesso(){            
                this.errors = []
                if(this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'){
                    if (!this.processo.num_processo) {
                      this.errors.push('Número é obrigatório');
                    }        
                    if (!this.processo.ds_processo) {
                      this.errors.push('Descrição é Obrigatório');
                    }
                    if (!this.selectedUsuarioPerfil) {
                      this.errors.push('Usuário é obrigatório');
                    }        
                }
                if(this.usuarioLogin.perfilUsuario==='FINALIZADOR'){
                    if (!this.processo.parecer) {
                      this.errors.push('Parecer é obrigatório');
                    }     
                }
                if(this.errors.length===0){
                    if(this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'){
                      this.processo.usuarioFinaliza={cd_usuario:this.selectedUsuarioPerfil}
                      this.processo.usuarioCadastro=this.usuarioLogin
                    }
                    if(!this.processo.cd_processo){
                      Processo.salvarProcesso(this.processo).then(response => {
                        response
                        this.processo={}
                        this.callMessage('Salvo com sucesso!' || this.usuarioLogin.nome)                        
                        this.listarProcesso()
                        this.closeModal('modalProcesso')
                      }).catch(error => {
                          console.log(error)
                      })
                    }else{ 
                      this.errors=[]                    
                      Processo.atualizarProcesso(this.processo).then(resposta => {
                        resposta
                        this.processo={}       
                        this.callMessage('Atualizado com sucesso!' || this.usuarioLogin.nome)     
                        this.listarProcesso()
                        this.closeModal('modalProcesso')
                      }).catch((error) => {
                          console.log(error)
                      })
                    }
                }

              },

              editarProcesso(processo){
                this.showModal('modalProcesso')                    
                this.processo=processo
              }               

  }

}

</script>


<style>
.pageIni{
  width:100%;display:block;font-size:5em;color:blue;vh:80%;line-height:80vh;text-align:center;
}
.titleNav{
  height:70px;font-size:20px;
}
.linkProd{
  float:right;margin-right:40px;display:flex;
}
.titleTab{
  color:#757575;font-size:1.4rem !important;font-weight:600;
}
.addUser,.addProcess{
  font-size:18px !important;color:#039be5;background:none !important;border:none;cursor:pointer;
}
.iconUser,.iconProcess{
  font-size:55px !important;line-height:60px;
}
.modal .modal-content {
    padding:12px;
}
.modalUser,.modalProcesso{
  border:1px solid #999;top:150px;width:550px !important;height:500px;z-index:9999;
}
.divSearch{
    float:right;
}
.iconSearch{
  font-size:24px !important;height:24px !important;line-height:24px !important;
}
.btSearch{
  margin-left:20px;padding:5px;margin-right:20px;
}
.toastApi{
  background-color:#1E88E5 !important;
}
label {
    font-size:1rem !important;
}
select {
    border:1px solid #9e9e9e;
}
table th{
    font-size:1.1rem;font-weight:600;color:grey;
}
[type="radio"]:checked+span:after, [type="radio"].with-gap:checked+span:after {
    background-color: #1E88E5 !important;
}

</style>

