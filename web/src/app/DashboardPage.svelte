<script lang="ts">
  import {onMount} from 'svelte'
  import {httpClient} from "../http/HttpClient";

  let token = "...."

  onMount(async () => {
    token = await httpClient.hello.helloRolesAllowedGet().then(x => x.data.value)
    // token = await httpClient.hello.helloPermitAllGet().then(x => x.data.value)
  })

</script>
<h1>Admin</h1>

User name: {token}
