<script>
  import Logo from './Logo.svelte'
  import Hamburger from './Hamburger.svelte'

  export let sidebar = false
</script>

<header>
  <nav>
    <ul>
      <li>
        <Hamburger bind:open={sidebar}/>
      </li>
      <li>
        <Logo/>
      </li>
    </ul>
    <ul>
      <li><a href="#">Link</a></li>
      <li><a href="#">Link</a></li>
      <li><a href="#">Link</a></li>
    </ul>
  </nav>
</header>
<style>
  nav {
    margin: 0;
    padding: 0;
    border: 0;
  }
</style>
