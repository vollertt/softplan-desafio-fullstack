<!--Template contendo as duas páginas Usuário e Cadatro e suas funções javascript do Vue.js e estilos -->
<template>

  <div>
    
    <nav>
      <div class="nav-wrapper blue darken-1">   
        <span class="brand-logo center titleNav">Desafio Softplan </span>   
        <div style="float:right;margin-right:40px;display:flex;">           
           <a href="#" @click.prevent="linkPro()" class="">{{linkProd}}</a>  
           <i class="material-icons">link</i>        
        </div>
        <div>
             <div style="margin-left:20px;">
              <input minlength="3" style="width:300px;background:white;" type="text" placeholder="Digite Email de Login" v-model="emailLogin">
              <button @click="buscarUsuarioLogin()" class="waves-effect btn-small blue darken-1 btSearch"><i class="material-icons center iconSearch">account_circle</i></button>
              {{this.usuarioLogin.perfilUsuario}}
            </div>
        </div>             
      </div>      
    </nav>

    <div class="container">
      <!--Pagina da aplicação associada ao Cadastro de usuário -->
      <div id="div-user" style="display:">  
          <div>         
            <a v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" v-on:click="showModal('modalUser')" class="addUser" href="#modalUser"><i class="large material-icons iconUser">account_circle</i>+[{{title}}]</a>            
            <div class="divSearch">              
              <input minlength="3" style="width:300px" type="text" placeholder="Busca" v-model="searchField">
              <button @click="pesquisarUsuario()" class="waves-effect btn-small blue darken-1 btSearch"><i class="material-icons center iconSearch">search</i></button>              
            </div>
          </div>      
          <div id="modalUser" class="modal modalUser">
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
                    <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" class="waves-effect waves-light btn-small blue ">Salvar<i class="material-icons left">save</i></button>
                    <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" type="button" v-on:click="closeModal('modalUser')" class="waves-effect waves-light btn-small blue ">Cancelar<i class="material-icons left">cancel</i></button>
                  </form>
            </div>        
          </div> 
          <div style="display:block">
           <span style="text-align: center;display:block;font-size:17px;font-weight:bold;">Lista de Usuários</span>        
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
                  <button v-if="usuarioLogin.perfilUsuario==='ADMIN'" @click="editarUsuario(usuario)" class="waves-effect btn-small blue"><i class="material-icons">create</i></button>
                </td>
              </tr>
            </tbody>      
          </table>
          </div>
      </div> 

      <!--Pagina da aplicação associada ao Cadastro de Processos -->
      <div id="div-process" style="display:none">  
          <div>        
            <a v-if="this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'" v-on:click="showModal('modalProcesso')" class="addProcess" href="#modalUser"><i class="large material-icons iconProcess">book</i>+[{{title}}]</a>
          </div>      
          <div id="modalProcesso" class="modal modalProcesso">
            <div class="modal-content">
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
                        <label>Parecer:</label>
                        <textarea placeholder="Parecer" v-model="processo.parecer"></textarea>
                        <label>Status:</label>
                        <select style="display:initial" id='status_finalizado' placeholder="Status Parecer" v-model="processo.status_finalizado">
                          <option value="true">Finalizado</option>
                          <option selected value="false">Aberto (em análise)</option>
                        </select>     
                    </div>                        
                    <br><br>
                    <button class="waves-effect waves-light btn-small blue ">Salvar<i class="material-icons left">save</i></button>
                    <button type="button" v-on:click="closeModal('modalProcesso')" class="waves-effect waves-light btn-small blue ">Cancelar<i class="material-icons left">cancel</i></button>
                  </form>

            </div>        
          </div>  
          <br>  
          <span style="text-align: center;display:block;font-size:17px;font-weight:bold;">Lista de Processos</span>      
          <table>
            <thead>
              <tr>
                <th>Numero</th>
                <th>Descrição</th>                
                <th>Status Parecer</th> 
                <th>Data Parecer</th>
                <th>Parecer</th>
                <th v-if="usuarioLogin.perfilUsuario!=='DISTRIBUIDOR'">Distribuidor</th>
                <th v-if="usuarioLogin.perfilUsuario!=='FINALIZADOR'">Finalizador</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="processo of processos" :key="processo.cd_processo">
                  <td>{{processo.num_processo}}</td>
                  <td>{{processo.ds_processo}}</td>                
                  <td v-if="processo.status_finalizado===true">Finalizado</td><td v-if="processo.status_finalizado===false">Aberto</td>                
                  <td>{{processo.dt_parecer_inc}}</td>
                  <td>{{processo.parecer}}</td>
                  <td v-if="usuarioLogin.perfilUsuario!=='DISTRIBUIDOR'">{{processo.cd_usuario_cadastro}}</td>
                  <td v-if="usuarioLogin.perfilUsuario!=='FINALIZADOR'">{{processo.cd_usuario_finaliza}}</td>
                  <td>
                    <button v-if="usuarioLogin.perfilUsuario==='FINALIZADOR' && processo.status_finalizado===false" @click="editarProcesso(processo)" class="waves-effect btn-small blue"><i class="material-icons">create</i></button>
                </td>
              </tr>
            </tbody>      
          </table>
      </div> 

    </div>

  </div>
</template>


<script>


import Usuario from './services/usuarios'
import Processo from './services/processos'


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
         dt_parecer_inc:'',
         cd_usuario_cadastro:'',
         cd_usuario_finaliza:''
       },
       selectedUsuarioPerfil:'',
       usuarios: [],
       processos:[],
       usuariosPerfil:[],
       errors: [],
       searchField:'',
       emailLogin:'',
       title:'Cadastro de Usuário',
       linkProd:'Processos'
     }
  },

  mounted(){
    this.listarUsuario();      
  },
  
  
  methods: {    
    /* Método para Alterar de Cadastro de usuário para de Processos */
    linkPro(){
      if(this.linkProd==='Processos'){
         this.linkProd='Usuários';
         this.title='Cadastro de Processos';
         this.usuarios=[];
         this.usuario=[];
         this.listarProcesso();
         document.getElementById('div-user').style.display='none';
         document.getElementById('div-process').style.display='';
      }else{
         this.linkProd='Processos';
         this.title='Cadastro de Usuários';
         this.listarUsuario();
         document.getElementById('div-user').style.display='';
         document.getElementById('div-process').style.display='none';
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
        console.log(this.usuario)        
      }else{
        if(!this.processo.cd_processo){
          this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_parecer_inc:'',cd_usuario_cadastro:'',cd_usuario_finaliza:''}
          this.errors=[]
        }
      }
     },

     closeModal: function(id) {
       if(this.linkProd==='Processos'){
          this.usuario={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
       }else{
         this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_parecer_inc:'',cd_usuario_cadastro:'',cd_usuario_finaliza:''}
       }
       this.errors=[]
       document.getElementById(id).style.display='none'
     },


    /*
    * Métodos Listar, Pesquisar, salvar e atualizar associados com a entidade Usuário e os serviços Spring Rest
    */          
           
          buscarUsuarioLogin(){
            this.usuario={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.usuarioLogin={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.usuariosPerfil={cd_usuario:'',nome:'',email:'',status:'',perfilUsuario:'',dt_cadastro:''}
            this.processo={cd_processo:'',num_processo:'',ds_processo:'',status_finalizado:false,parecer:'',dt_parecer_inc:'',cd_usuario_cadastro:'',cd_usuario_finaliza:''}
            this.selectedUsuarioPerfil=''
            this.errors=[]
            this.searchField=''
            this.title='Cadastro de Usuário'
            this.linkProd='Processos'
            this.errors=[]
            this.processos=[]
            this.usuarios=[];
            document.getElementById('div-user').style.display='';
            document.getElementById('div-process').style.display='none';
            Usuario.buscarUsuarioLogin(this.emailLogin).then(resposta => {
              this.usuarioLogin = resposta.data
              this.listarUsuario()
            })            
          },
          
          listarUsuario(){
            console.log(this.usuarioLogin.cd_usuario)
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
                      alert('Salvo com sucesso!')
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
                      alert('Atualizado com sucesso!')
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
        * Métodos Listar, Pesquisar, salvar e atualizar associados com a entidade Processo e os serviços Spring Boot JPA / Rest
       */
          listarProcesso(){
            if(this.usuarioLogin.perfilUsuario!=='ADMIN'){
              Processo.pesquisarProcessoUsuarioLogin(this.usuarioLogin.email).then(resposta => {
                this.processos = resposta.data
              })
              if(this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'){
                Usuario.listaUsuarioPerfil('FINALIZADOR').then(resposta => {
                  this.usuariosPerfil = resposta.data
                })
              }
            }else{
              Processo.listarProcesso().then(resposta => {
                this.processos = resposta.data
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
                    this.processo.cd_usuario_finaliza=this.selectedUsuarioPerfil
                    this.processo.cd_usuario_cadastro=this.usuarioLogin.cd_usuario
                  }
                  if(!this.processo.cd_processo){
                    Processo.salvarProcesso(this.processo).then(response => {
                      response
                      this.processo={}
                      alert('Salvo com sucesso!')
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
                      alert('Atualizado com sucesso!')
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
              //document.getElementById('status').style.display='initial'      
              this.processo=processo
            },

               

  }

}

</script>

<style>
.titleNav{
  height:70px;
  font-size:20px;
}
.addUser{
  font-size:40px !important;color: #039be5;
}
.iconUser{
  font-size:70px !important;line-height:70px;
}
.modal .modal-content {
    padding: 12px;
}
.modalUser{
  border:1px solid #999;top:150px;width:500px;height:500px;z-index:9999
}
.modalProcesso{
  border:1px solid #999;top:150px;width:500px;height:500px;z-index:9999
}
.addProcess{
  font-size:25px !important;color: #039be5;
}
.iconProcess{
  font-size:50px !important;line-height:50px;
}
.divSearch{
  text-align: right;
}
.iconSearch{
  font-size:24px !important;height:24px !important;line-height:24px !important;
}
.btSearch{
  margin-left:20px;padding:5px
}
</style>

