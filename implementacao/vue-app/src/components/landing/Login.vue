<template>
<div>
  <b-form inline>
    <b-input
      id="inline-form-input-name"
      class="mb-2 mr-sm-2 mb-sm-0"
      placeholder="Username" v-model="username"
    ></b-input>
    <b-input id="inline-form-input-username" placeholder="Password" v-model="password"></b-input>
    
    <b-button variant="primary" v-on:click="logar">Save</b-button>
  </b-form>
</div>
</template>
<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        data: [],
        labelPosition: 'right',
          username: 'admin',
          password: 'admin'
        
      }
    },
    mounted () {
      
  },
  methods: {
   
    logar () {
      console.log("USUARIO: " + this.username + " SENHA: "+ this.password)
      axios.post('http://localhost:8081/login',{"username":this.username,"password":this.password}
       ).then(({ data }) => {
          console.log(data);
          localStorage.setItem('token', data.token);
          this.$router.push('/app') 
        }).catch(({ error }) => {
           console.log("ERRO");
          console.log(error);
        }).finally(() => {});
      }
    }
  };
</script>