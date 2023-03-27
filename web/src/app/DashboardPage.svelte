<script lang="ts">
  import {onMount} from 'svelte'
  import {httpClient} from "../http/HttpClient";
  import PLButton from "../components/PLButton.svelte";

  let result = "...."

  async function refresh() {
    result = "....";
    httpClient.hello.helloRolesAllowedGet().then(x => {
      result = x.data.value
    })
  }

  onMount(async () => {
    await refresh();
    // token = await httpClient.hello.helloPermitAllGet().then(x => x.data.value)
  })

</script>
<h1>Admin</h1>

User name: {result}
<p></p>

<PLButton on:click={refresh}>Refresh</PLButton>
