import HomeView from "@/views/HomeView.vue";
import BlogView from "@/views/other/BlogView.vue";
import MusicView from "@/views/other/MusicView.vue";
import CloudDisksView from "@/views/other/CloudDisksView.vue";

export const routes = [
  { path: "/home", redirect: "/" },
  { path: "/", name: "主页", component: HomeView },
  { path: "/blog", name: "博客", component: BlogView },
  { path: "/music", name: "音乐", component: MusicView },
  { path: "/cloud", name: "云盘", component: CloudDisksView },
];
