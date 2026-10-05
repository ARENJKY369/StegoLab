import type { Config } from 'tailwindcss'
const config: Config = { content: ['./app/**/*.{ts,tsx}'], theme: { extend: { colors: { ink: '#101214', surface: '#171a1d', panel: '#1e2327', line: '#30373c', copy: '#e8e7e2', muted: '#929a9d', accent: '#9ab9b0', danger: '#d98585', success: '#9dc2a3' }, fontFamily: { sans: ['var(--font-inter)'], mono: ['var(--font-jetbrains)'] }, borderRadius: { soft: '10px' } } }, plugins: [] }
export default config
