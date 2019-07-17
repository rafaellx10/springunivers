<template>
<div>
  <b-button variant="primary" v-on:click="listar">Buscar</b-button>
  <div>
    <b-table striped hover :items="items"></b-table>
  </div>
  <div>
    <b-table striped hover :items="items" :fields="fields"></b-table>
  </div>
</div>
</template>
<script>
  import axios from 'axios';
  export default {
    data() {
      return {
        items: [],
        fields: [
          {
            key: 'id',
            sortable: true
          },
          {
            key: 'nome',
            sortable: true
          },
          ,
          {
            key: 'sobrenome',
            sortable: true
          },
          {
            key: 'telefone.ddd',
            label: 'DDD'
          },
          {
            key: 'telefone.numero',
            label: 'Numero'
          }
        ]
      }
    },
    mounted () {
      
  },
  methods: {
    listar(){
        let token=  localStorage.getItem('token');
        console.log(token);
        axios.get('http://localhost:8081/cadastro/contatos',{headers: {'Authorization': token}})
        .then(({ data }) => {
          console.log(data);
          this.items = data;
        }).catch(({ err }) =>{
          console.log(err);  
        }).finally(() => {})
      }
    }
  };
</script>