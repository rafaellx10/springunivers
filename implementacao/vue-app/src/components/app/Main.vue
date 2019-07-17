<template>
     <div>
    <b-container>
      <b-jumbotron
        header="Tela do Sistema"
        lead="Demonstração dos recursos de Vue.js (Rotas, APIs e Etc)"
      >
        <p>Template legal: https://github.com/coreui/coreui-free-vue-admin-template</p>
        <b-nav>
          <b-nav-item active>
            <router-link to="/contatos">Listar Contatos</router-link>
          </b-nav-item>
           <b-nav-item active>
            <router-link to="/login">Logoff</router-link>
          </b-nav-item>
        </b-nav>
      </b-jumbotron>

      <router-view />
    </b-container>
  </div>
   
</template>
<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        
      }
    },
    mounted () {
      
  },
  methods: {
    }
  };
</script>