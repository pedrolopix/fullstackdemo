<script lang="ts">
  import {authService} from "./AuthService";
  import {login} from "./AuthStore";
  import PLButton from "../components/PLButton.svelte";
  import PLInput from "../components/PLInput.svelte";


  let rememberMe: Boolean = !!($login?.username || $login?.password);
  let username = $login?.username
  let password = $login?.password

  const doLogin = () => {
    authService.doLogin(username, password, rememberMe)
  }


</script>

<section class="h-screen">
  <div class="container h-full px-6 py-24 bg-neutral-100 ">
    <div
        class="g-6 flex h-full flex-wrap items-center justify-center lg:justify-between ">
      <div class="mb-12 md:mb-0 md:w-8/12 lg:w-6/12">
        <img
            src="https://tecdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/draw2.svg"
            class="w-full"
            alt="login image"/>
      </div>
      <div class="md:w-8/12 lg:ml-6 lg:w-5/12">
        <form on:submit|preventDefault={doLogin}>
          <PLInput label="User name" bind:value={username} type="text" required></PLInput>
          <PLInput label="Password" bind:value={password} type="password" required></PLInput>
          <div class="mb-6 flex items-center justify-between">
            <PLInput labelRight="Remember Me" bind:value="{rememberMe}" type="toogle"/>
            <a
                href="#!"
                class="text-primary transition duration-150 ease-in-out hover:text-primary-600 focus:text-primary-600 active:text-primary-700 dark:text-primary-400 dark:hover:text-primary-500 dark:focus:text-primary-500 dark:active:text-primary-600"
            >Forgot password?</a>
          </div>
          <PLButton>Sign in</PLButton>
        </form>
      </div>
    </div>
  </div>
</section>
