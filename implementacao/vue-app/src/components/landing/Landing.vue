<template>
  <div>
    <b-container>
      <b-jumbotron
        header="Spring Vue.js"
        lead="Demonstração dos recursos de Vue.js (Rotas, APIs e Etc)"
      >
        <p>Template legal: https://github.com/coreui/coreui-free-vue-admin-template</p>
        <b-nav>
          <b-nav-item active>
            <router-link to="/home">Home</router-link>
          </b-nav-item>
          <b-nav-item>
            <router-link to="/login">Login</router-link>
          </b-nav-item>
          <b-nav-item>Another Link</b-nav-item>
        </b-nav>
      </b-jumbotron>

      <router-view />
    </b-container>
  </div>
</template>
<script>
</script>