<script>
  export let href
</script>

<a {href} {...$$restProps}>
  <slot {...$$restProps} />
</a>
