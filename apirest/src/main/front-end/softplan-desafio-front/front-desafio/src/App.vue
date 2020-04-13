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
                    <button v-if="this.usuarioLogin.perfilUsuario==='ADMIN'" type="button" @click="closeModal('modalUser')" class="waves-effect waves-light btn-small blue ">Cancelar<i class="material-icons left">cancel</i></button>
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
                <td>{{usuario.status}}</td>
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
            <a v-if="this.usuarioLogin.perfilUsuario==='DISTRIBUIDOR'" v-on:click="showModal('modalProcesso')" class="addUser" href="#modalUser"><i class="large material-icons iconUser">book</i>+[{{title}}]</a>
          </div>      
          <div id="modalProcesso" class="modal modalProcesso">
            <div class="modal-content">
                  <h5>Cadastro de Processo</h5>
                  <ul>
                    <li v-for="(erro,index) of errors" :key="index">
                      <b>{{erro}}</b> 
                    </li>
                  </ul>                  
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
                  <td>{{processo.status_finalizado}}</td>               
                  <td>{{processo.dt_parecer_inc}}</td>
                  <td>{{processo.parecer}}</td>
                  <td v-if="usuarioLogin.perfilUsuario!=='DISTRIBUIDOR'">{{processo.cd_usuario_cadastro}}</td>
                  <td v-if="usuarioLogin.perfilUsuario!=='FINALIZADOR'">{{processo.cd_usuario_finaliza}}</td>
                  <td>
                    <button v-if="usuarioLogin.perfilUsuario==='FINALIZADOR'" @click="editarProcesso(processo)" class="waves-effect btn-small blue"><i class="material-icons">create</i></button>
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
       usuarioLogin:{
         cd_usuario:'',
         nome:'',
         email:'',
         status:'',
         perfilUsuario:'',
         dt_cadastro:''
       },
       usuario:{
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
       usuarios: [],
       processos:[],
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
      if(!this.usuario.cd_usuario){
        document.getElementById('status').style.display='none'
        this.usuario={}
        this.errors=[]
      }else{
        document.getElementById('status').style.display='initial'
      }
     },
     closeModal: function(id) {
       this.usuario={}
       document.getElementById(id).style.display='none'
     },


    /*
    * Métodos Listar, Pesquisar, salvar e atualizar associados com a entidade Usuário e os serviços Spring Rest
    */          
           
          buscarUsuarioLogin(){
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
        * Métodos Listar, Pesquisar, salvar e atualizar associados com a entidade Processo e os serviços Spring Rest
       */
          listarProcesso(){
            if(this.usuarioLogin.perfilUsuario!=='ADMIN'){
              Processo.pesquisarProcessoUsuarioLogin(this.usuarioLogin.email).then(resposta => {
                this.processos = resposta.data
              })
            }else{
              Processo.listarProcesso().then(resposta => {
                this.processos = resposta.data
              })
            }
          },          

          salvarProcesso(){
            
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

            editarProcesso(processo){
              this.showModal('modalProcesso')  
              document.getElementById('status').style.display='initial'      
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
  border:1px solid #999;top:150px;width:500px;height:450px;z-index:9999
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

