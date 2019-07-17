<template>
<div>

ola
</div>
</template>
<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        data: [],
        labelPosition: 'right',
        form: {
          username: '',
          password: ''
        }
      }
    },
    mounted () {
      
  },
  methods: {
    submitForm() {
        console.log(this.form.username);
      },
    logar () {
        console.log(this.form.username + "---"+ this.form.password)
       axios.post('http://localhost:8081/login',{"username":this.form.username,"password":this.form.password}
       ).then(({ data }) => {
          console.log(data);
          //LocalStorage.set('token', data.token);
          //this.$router.push({path: '/app'})
        }).catch(({ error }) => {
           console.log("ERRO");
          console.log(error);
        }).finally(() => {});
      }
    }
  };
</script>